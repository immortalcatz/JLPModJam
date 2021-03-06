package dreamLand.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dreamLand.DreamLand;
import dreamLand.utils.Reference;

public class BlockNMLogs extends Block{
    /** The type of tree this log came from. */
    public static final String[] NightMareWoodType = new String[] {"ash", "dark", "infernal", "death"};
    public static final String[] NMTreeTextureSides = new String[] {"ash_tree_side", "dark_tree_side", "infernal_tree_side", "death_tree_side"};
    public static final String[] NMTreeTextureTops = new String[] {"ash_tree_top", "dark_tree_top", "infernal_tree_top", "death_tree_top"};
    
    @SideOnly(Side.CLIENT)
    private Icon[] iconArrayTS;
    @SideOnly(Side.CLIENT)
    private Icon[] iconArrayTT;
    @SideOnly(Side.CLIENT)
    private Icon tree_top;
    protected BlockNMLogs(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(DreamLand.tabDreamLand);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 31;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return this.blockID;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte b0 = 4;
        int j1 = b0 + 1;

        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1))
        {
            for (int k1 = -b0; k1 <= b0; ++k1)
            {
                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        int j2 = par1World.getBlockId(par2 + k1, par3 + l1, par4 + i2);

                        if (Block.blocksList[j2] != null)
                        {
                            Block.blocksList[j2].beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int side, float par6, float par7, float par8, int meta)
    {
        int j1 = meta & 3;
        byte b0 = 0;

        switch (side)
        {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int meta)
    {
        int k = meta & 12;
        int l = meta & 3;
        //System.out.println("k= "+ k);
        //System.out.println("l= " + l);
        return k == 0 && (side == 1 || side == 0) ? this.iconArrayTT[l] : (k == 4 && (side == 5 || side == 4) ? this.iconArrayTT[l] : (k == 8 && (side == 2 || side == 3) ? this.iconArrayTT[l] : this.iconArrayTS[l]));
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, @SuppressWarnings("rawtypes") List par3List)
    {
        for(int i = 0; i < NightMareWoodType.length; i++){
            par3List.add(new ItemStack(par1, 1, i));
            }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, limitToValidMetadata(par1));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        //this.tree_top = par1IconRegister.registerIcon(Archive.texture + "sparkling_tree_top");
        this.iconArrayTS = new Icon[NMTreeTextureSides.length];
        this.iconArrayTT = new Icon[NMTreeTextureTops.length];
        
        for (int j = 0; j < this.iconArrayTT.length; ++j)
        {
            this.iconArrayTT[j] = par1IconRegister.registerIcon(Reference.texture + NMTreeTextureTops[j]);
        }
        for (int i = 0; i < this.iconArrayTS.length; ++i)
        {
            this.iconArrayTS[i] = par1IconRegister.registerIcon(Reference.texture + NMTreeTextureSides[i]);
        }
    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z)
    {
        return true;
    }
}
