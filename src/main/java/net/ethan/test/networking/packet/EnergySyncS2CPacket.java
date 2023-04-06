package net.ethan.test.networking.packet;

import net.ethan.test.block.entity.SinkBlockEntity;
import net.ethan.test.client.ClientThirstData;
import net.ethan.test.screen.SinkMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySyncS2CPacket {
    private final int energy;
    private final BlockPos pos;
    public EnergySyncS2CPacket(int energy, BlockPos pos) {
        this.pos = pos;
        this.energy = energy;
    }
    public EnergySyncS2CPacket(FriendlyByteBuf buf){
        this.pos = buf.readBlockPos();
        this.energy = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeBlockPos(pos);
        buf.writeInt(energy);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SinkBlockEntity blockEntity){
                blockEntity.setEnergyLevel(energy);

                if (Minecraft.getInstance().player.containerMenu instanceof SinkMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)){
                    blockEntity.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }

}
