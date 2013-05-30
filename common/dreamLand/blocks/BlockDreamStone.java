package dreamLand.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dreamLand.DreamLand;
import dreamLand.utils.Archive;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockDreamStone extends Block{

    @SideOnly(Side.CLIENT)
    private Icon icon[];
    
    public BlockDreamStone(int par1) {
        super(par1, Material.rock);
        this.setCreativeTab(DreamLand.tabDreamLand);
    }
    @Override
    public Icon getIcon(int side, int meta)
    {
        return icon[meta];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
        icon = new Icon[4];
        icon[0] = iconRegister.registerIcon(Archive.texture + Archive.dreamCobble);
        icon[1] = iconRegister.registerIcon(Archive.texture + Archive.nmCobble); 
        icon[2] = iconRegister.registerIcon(Archive.texture + Archive.bloodCobble);
        icon[3] = iconRegister.registerIcon(Archive.texture + Archive.dreamStoneBrick);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, @SuppressWarnings("rawtypes") List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        
    }

}
