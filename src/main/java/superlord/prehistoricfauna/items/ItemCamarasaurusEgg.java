package superlord.prehistoricfauna.items;




import net.minecraft.entity.Entity;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.EnumActionResult;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumHand;

import net.minecraft.util.math.BlockPos;

import net.minecraft.world.World;
import superlord.prehistoricfauna.Main;
import superlord.prehistoricfauna.entity.EntityCamarasaurus;
import superlord.prehistoricfauna.entity.EntityDinosaurEgg;
import superlord.prehistoricfauna.entity.EntityExtinct;
import superlord.prehistoricfauna.entity.EntityType;
import superlord.prehistoricfauna.message.MessageUpdateEgg;



public class ItemCamarasaurusEgg extends PrehistoricEntityItem {

    public ItemCamarasaurusEgg(EntityType type) {

        super("camarasaurus_egg", type);

        this.setHasSubtypes(true);

        this.setMaxDamage(0);

        this.maxStackSize = 1;

    }



    @Override

    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        BlockPos blockpos1 = pos.up();

        boolean flag1 = !world.isAirBlock(blockpos1) && !world.getBlockState(blockpos1).getBlock().isReplaceable(world, blockpos1);

        if (flag1) {

            return EnumActionResult.FAIL;

        }

        BlockPos offset = pos.offset(facing);

        boolean success = this.spawnEgg(world, player, this.type, offset.getX() + 0.5F, offset.getY() + 0.5F, offset.getZ() + 0.5F);

        if (success && !player.capabilities.isCreativeMode) {

            player.getHeldItem(hand).shrink(1);

        }

        return success ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

    }



    private boolean spawnEgg(World world, EntityPlayer player, EntityType type, double x, double y, double z) {

        Entity egg;

        if (!type.isVivariousAquatic()) {

            egg = new EntityDinosaurEgg(world, type);

            egg.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);

            if (!world.isRemote) {

                world.spawnEntity(egg);

            }

            ((EntityDinosaurEgg) egg).selfType = type;

            if (!world.isRemote) {

                Main.NETWORK_WRAPPER.sendToAll(new MessageUpdateEgg(egg.getEntityId(), type.ordinal()));

            }

            return true;

        } else {

            egg = type.invokeClass(world);

            if (egg != null) {

                egg.setLocationAndAngles(x, y, z, world.rand.nextFloat() * 360.0F, 0.0F);



                if (egg instanceof EntityExtinct) {

                	EntityExtinct prehistoric = (EntityExtinct) egg;

                    prehistoric.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(prehistoric)), null);

                    prehistoric.setAgeInDays(0);

                    EntityCamarasaurus entitychicken = new EntityCamarasaurus(world);
                    entitychicken.setGrowingAge(-24000);

                }

                if (!world.isRemote) {

                    world.spawnEntity(egg);

                }

            }

        }

        return egg != null;

    }

}