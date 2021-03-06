package dreamLand.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dreamLand.DreamLand;
import dreamLand.utils.Reference;
import dreamLand.utils.Config;

public class ItemDreamSword extends ItemSword {

    public ItemDreamSword(int var1, EnumToolMaterial var2) {
        super(var1, var2);
        this.setCreativeTab(DreamLand.tabDreamLand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(Reference.texture
                + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y,
            int z, int par7, float par8, float par9, float par10) {

        world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.pop", 1.0F,
                (itemRand.nextFloat() * 0.4F) + 0.8F);

        return makeCake(x, y, z, world, itemStack, player);
    }

    public boolean makeCake(int x, int y, int z, World world, ItemStack itemStack,
            EntityPlayer player) {

        int[] id = Config.ids.getIntList();

        List<Integer> ids = new ArrayList<Integer>();
        for (int i : id)
            ids.add(i);

        if (ids.contains(world.getBlockId(x, y, z))){
            itemStack.damageItem(1, player);
            return world.setBlock(x, y, z, Block.cake.blockID);
        }
        return false;
    }
}