package net.ethan.test.networking.packet;

import net.ethan.test.block.entity.SinkBlockEntity;
import net.ethan.test.screen.SinkMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;
    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.pos = pos;
        this.fluidStack = fluidStack;
    }
    public FluidSyncS2CPacket(FriendlyByteBuf buf){
        this.pos = buf.readBlockPos();
        this.fluidStack = buf.readFluidStack();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeBlockPos(pos);
        buf.writeFluidStack(fluidStack);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SinkBlockEntity blockEntity){
                blockEntity.setFluid(fluidStack);

                if (Minecraft.getInstance().player.containerMenu instanceof SinkMenu menu &&
                        menu.getBlockEntity().getBlockPos().equals(pos)){
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }

}
