package fermiummixins.handlers.bountifulbaubles.flare;

import fermiummixins.FermiumMixins;
import fermiummixins.mixin.bountifulbaubles.vanilla.IEntityArrowAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityFlareNonAlbedo extends EntityArrow {
    
    private boolean isPlayingSound = false;

    public EntityFlareNonAlbedo(World world) {
        super(world);
    }

    public EntityFlareNonAlbedo(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityFlareNonAlbedo(World world, EntityLivingBase thrower) {
        this(world, thrower.posX, thrower.posY+(double)thrower.getEyeHeight()-0.10000000149011612D, thrower.posZ);
        this.shootingEntity = thrower;
    }

    @Override
    protected void arrowHit(EntityLivingBase entity) {
        if(!(entity instanceof EntityEnderman)) entity.setFire(5);
    }

    protected float getGravityVelocity() {
        return 0.03F;
    }

    @Override
    public void onUpdate() {
        super.onEntityUpdate();

        if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(this.motionX*this.motionX + this.motionZ*this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D/Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D/Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }

        int particleRate = this.inGround ? 10 : 2;

        if(this.world.isRemote) {
            Vec3d vel = new Vec3d(0, 0, -1).rotatePitch((float) (this.rotationPitch*(Math.PI/180D)))
                    .rotateYaw((float) (this.rotationYaw*(Math.PI/180D)));
            if((this.world.getTotalWorldTime()%particleRate)==0) {
                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX+vel.x*0.3,
                        this.posY+vel.y*0.3, this.posZ+vel.z*0.3, vel.x*0.055, vel.y*0.055,
                        vel.z*0.055);
            }

            if(!this.isPlayingSound) {
                this.isPlayingSound = true;
                FermiumMixins.PROXY.playSoundFlare(this);
            }
        }

        if(this.ticksExisted > 6000) {
            this.setDead();
        }
        
        BlockPos blockpos = new BlockPos(((IEntityArrowAccessor)this).getXTile(), ((IEntityArrowAccessor)this).getYTile(), ((IEntityArrowAccessor)this).getZTile());
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        
        if(iblockstate.getMaterial() != Material.AIR) {
            AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);
            if(axisalignedbb != null && axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }
        
        if(this.inGround) {
            if(block == ((IEntityArrowAccessor)this).getInTile()) {
                ((IEntityArrowAccessor)this).setTicksInGround(((IEntityArrowAccessor)this).getTicksInGround() + 1);
                return;
            }

            this.inGround = false;
            this.motionX *= this.rand.nextFloat() * 0.2F;
            this.motionY *= this.rand.nextFloat() * 0.2F;
            this.motionZ *= this.rand.nextFloat() * 0.2F;
        }

        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX+this.motionX, this.posY+this.motionY, this.posZ+this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX+this.motionX, this.posY+this.motionY, this.posZ+this.motionZ);

        if(raytraceresult != null) {
            vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this,
                this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double d0 = 0.0D;

        for(Entity entity1 : list) {
            if(entity1.canBeCollidedWith()) {
                if(entity1 != this.shootingEntity || this.ticksExisted >= 5) {
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                    RayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                    if(raytraceresult1 != null) {
                        double d1 = vec3d.squareDistanceTo(raytraceresult1.hitVec);

                        if(d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }
        }

        if(entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }

        if(raytraceresult != null) {
            if(raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && this.world.getBlockState(raytraceresult.getBlockPos()).getBlock()==Blocks.PORTAL) {
                this.setPortal(raytraceresult.getBlockPos());
            }
            else if(!net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f = MathHelper.sqrt(this.motionX*this.motionX+this.motionZ*this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ)*(180D/Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f)*(180D/Math.PI));

        double velMultiplier = 0.995;
        float gravity = this.getGravityVelocity();

        if(this.isInWater()) {
            for(int j = 0; j < 4; ++j) {
                this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE,
                        this.posX-this.motionX*0.25D, this.posY-this.motionY*0.25D,
                        this.posZ-this.motionZ*0.25D, this.motionX, this.motionY, this.motionZ);
            }

            velMultiplier = 0.8;
        }

        this.motionX *= velMultiplier;
        this.motionY *= velMultiplier;
        this.motionZ *= velMultiplier;

        if(!this.hasNoGravity()) {
            if(this.motionY > 0) this.motionY -= gravity;
            else {
                double preY = this.motionY;
                this.motionY -= (double) gravity/8;
                this.motionX *= 0.95;
                this.motionY *= 1.01;
                this.motionZ *= 0.95;

                if(this.motionY - preY > gravity/4) this.motionY = preY - gravity/4;
            }
        }

        this.setPosition(this.posX, this.posY, this.posZ);
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        Entity entity = raytraceResultIn.entityHit;
        if(entity != null) {
            if(entity.world.isRemote && this.ticksExisted < 5) return;

            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            int i = MathHelper.ceil((double)f * 0.5D);

            DamageSource damagesource;

            if(this.shootingEntity == null) damagesource = DamageSource.causeArrowDamage(this, this);
            else damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);

            if(entity.attackEntityFrom(damagesource, (float)i)) {
                if(entity instanceof EntityLivingBase) {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    this.arrowHit(entitylivingbase);

                    if(this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }

                this.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            }

            this.motionX *= -0.10000000149011612D;
            this.motionY *= -0.10000000149011612D;
            this.motionZ *= -0.10000000149011612D;
            this.rotationYaw += 180.0F;
            this.prevRotationYaw += 180.0F;
        }
        else {
            BlockPos blockpos = raytraceResultIn.getBlockPos();
            ((IEntityArrowAccessor)this).setXTile(blockpos.getX());
            ((IEntityArrowAccessor)this).setYTile(blockpos.getY());
            ((IEntityArrowAccessor)this).setZTile(blockpos.getZ());
            IBlockState iblockstate = this.world.getBlockState(blockpos);
            ((IEntityArrowAccessor)this).setInTile(iblockstate.getBlock());
//          this.inData = this.inTile.getMetaFromState(iblockstate);
            this.motionX = (double) ((float) (raytraceResultIn.hitVec.x-this.posX));
            this.motionY = (double) ((float) (raytraceResultIn.hitVec.y-this.posY));
            this.motionZ = (double) ((float) (raytraceResultIn.hitVec.z-this.posZ));
            float f2 = MathHelper.sqrt(this.motionX*this.motionX+this.motionY*this.motionY+this.motionZ*this.motionZ);
            this.posX -= this.motionX/(double) f2*0.05000000074505806D;
            this.posY -= this.motionY/(double) f2*0.05000000074505806D;
            this.posZ -= this.motionZ/(double) f2*0.05000000074505806D;
            this.inGround = true;

            if(iblockstate.getMaterial() != Material.AIR) {
                ((IEntityArrowAccessor)this).getInTile().onEntityCollision(this.world, blockpos, iblockstate, this);
                if(((IEntityArrowAccessor)this).getTicksInGround() < 120 && this.world.isAirBlock(blockpos.offset(raytraceResultIn.sideHit))) this.world.setBlockState(blockpos.offset(raytraceResultIn.sideHit), Blocks.FIRE.getDefaultState(), 11);
            }
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }
}