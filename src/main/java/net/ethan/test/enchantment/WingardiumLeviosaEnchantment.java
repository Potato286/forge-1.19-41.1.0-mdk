package net.ethan.test.enchantment;

import net.ethan.test.networking.ModMessages;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class WingardiumLeviosaEnchantment extends Enchantment {
    protected WingardiumLeviosaEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if(!pAttacker.level.isClientSide()) {
            ServerLevel world = (ServerLevel)pAttacker.level;
            ServerPlayer player = ((ServerPlayer) pAttacker);
            Entity entity = pTarget;
            LivingEntity entity1 = pAttacker;
            if(pLevel == 1){
                entity.setPos(entity.getX(), entity.getY()+10, entity.getZ());
            }
            if(pLevel == 2){
                entity.setPos(entity.getX(), entity.getY()+15, entity.getZ());
            }
            if(pLevel == 3){
                entity.setPos(entity.getX(), entity.getY()+20, entity.getZ());
            }
            if(pLevel == 4){
                entity.setPos(entity.getX(), entity.getY()+25, entity.getZ());
            }
            if(pLevel == 5){
                entity.setPos(entity.getX(), entity.getY()+30, entity.getZ());
            }
            if(pLevel == 6){
                entity.setPos(entity.getX(), entity.getY()+35, entity.getZ());
            }
            if(pLevel == 7){
                entity.setPos(entity.getX(), entity.getY()+40, entity.getZ());
            }
            if(pLevel == 8){
                entity.setPos(entity.getX(), entity.getY()+45, entity.getZ());
            }
            if(pLevel == 9){
                entity.setPos(entity.getX(), entity.getY()+50, entity.getZ());
            }
            if(pLevel == 10){
                entity.setPos(entity.getX(), entity.getY()+55, entity.getZ());
            }
        }

        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }
}
