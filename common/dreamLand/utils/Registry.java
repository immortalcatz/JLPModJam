package dreamLand.utils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import dreamLand.blocks.ModBlocks;
import dreamLand.entity.EntityNMCreeper;
import dreamLand.entity.EntityPhoenix;
import dreamLand.entity.EntitySonicCreeper;
import dreamLand.items.ItemBlockDreamWall;
import dreamLand.items.ItemBlockFalling;
import dreamLand.items.ItemDreamFlowers;
import dreamLand.items.ItemDreamOres;
import dreamLand.items.ItemDreamStone;
import dreamLand.items.ItemDreamStoneSlabs;
import dreamLand.items.ItemDreamTrees;
import dreamLand.items.ItemDreamWoodBlock;
import dreamLand.items.ItemNMTrees;
import dreamLand.items.ModItems;
import dreamLand.render.RenderChickenAlter;
import dreamLand.tileEntity.TileChickenAlter;
import dreamLand.utils.handlers.DreamLandFuelHandler;
import dreamLand.utils.handlers.DreamLand_EventBonemeal;
import dreamLand.utils.handlers.EventHookHandler;
import dreamLand.utils.handlers.RecipeHandler;
import dreamLand.world.DreamLandWorldProvider;
import dreamLand.world.NightMareWorldProvider;
import dreamLand.world.biome.ModBiomes;
import dreamLand.world.village.VillagerTrades;

public class Registry {

    public static void register() {

        registerBlocks();

        registerItems();

        registerFuels();
        
        registerTileEntities();

        registerMobs();
        
        registerVillagers();

        registerDimension();

        registerEvents();

        addHarvestLevels();

        RecipeHandler.add();
    }

    private static void registerBlocks() {

        GameRegistry.registerBlock(ModBlocks.portalObsidian, "Obsidiatal");

        GameRegistry.registerBlock(ModBlocks.portal, "DreamLand_Portal");

        GameRegistry.registerBlock(ModBlocks.dreamStone, "Dream_Stone");

        GameRegistry.registerBlock(ModBlocks.dreamStones, ItemDreamStone.class,"Dream_Stones");

        GameRegistry.registerBlock(ModBlocks.dreamDirt, "Dream_Dirt");

        GameRegistry.registerBlock(ModBlocks.dreamGrass, "Dream_Grass");

        GameRegistry.registerBlock(ModBlocks.oreLucidity, "OreLucidity");

        GameRegistry.registerBlock(ModBlocks.oreBlocks, ItemDreamOres.class, "oreBlocks");

        GameRegistry.registerBlock(ModBlocks.dreamCobble, "Dream_Cobble");

        GameRegistry.registerBlock(ModBlocks.dreamStairs, "Dream_Stairs");

        GameRegistry.registerBlock(ModBlocks.dreamLogs, ItemDreamTrees.class, "Dream_Logs");

        GameRegistry.registerBlock(ModBlocks.nmLogs, ItemNMTrees.class, "NM_Logs");

        GameRegistry.registerBlock(ModBlocks.dreamPlanks, ItemDreamWoodBlock.class,"Dream_Planks");

        GameRegistry.registerBlock(ModBlocks.dreamLeaves, ItemDreamWoodBlock.class, "Dream_Leaves");

        GameRegistry.registerBlock(ModBlocks.dreamSaplings, ItemDreamWoodBlock.class,"Dream_Saplings");

        GameRegistry.registerBlock(ModBlocks.dreamWall, ItemBlockDreamWall.class, "Dream_Wall");

        GameRegistry.registerBlock(ModBlocks.dreamFalling, ItemBlockFalling.class, "Dream_Falling");

        GameRegistry.registerBlock(ModBlocks.nmPortal, "NightMare_Portal");

        GameRegistry.registerBlock(ModBlocks.nmGrass, "NM_Grass");

        GameRegistry.registerBlock(ModBlocks.nmDirt, "NM_Dirt");

        GameRegistry.registerBlock(ModBlocks.nmStone, "NM_Stone");

        GameRegistry.registerBlock(ModBlocks.dreamFence, ItemDreamWoodBlock.class, "Dream_Fence");

        //GameRegistry.registerBlock(ModBlocks.dreamWoodSlabs, ItemDreamSlabs.class, "Dream_Wood_Slabs");

        //GameRegistry.registerBlock(ModBlocks.dreamStoneSlabs, ItemDreamStoneSlabs.class, "Dream_Stone_Slabs");

        //GameRegistry.registerBlock(ModBlocks.dreamWoodDBLSlabs, ItemDreamSlabs.class, "Dream_Wood_DBL_Slabs");

        //GameRegistry.registerBlock(ModBlocks.dreamStoneDBLSlabs, ItemDreamStoneSlabs.class, "Dream_Stone_DBL_Slabs");

        GameRegistry.registerBlock(ModBlocks.nmCobble, "NM_Cobble");

        GameRegistry.registerBlock(ModBlocks.nmStairs, "NM_Stairs");

        GameRegistry.registerBlock(ModBlocks.bloodStairs, "Blood_Stairs");

        GameRegistry.registerBlock(ModBlocks.oreMethaneClathrate, "Ore_Methane_Clathrate");
        
        GameRegistry.registerBlock(ModBlocks.dreamFlowers, ItemDreamFlowers.class, "Dream_Flowers");

        GameRegistry.registerBlock(ModBlocks.chickenAlter, "Chicken_Alter");
    }
    /*
     * Register Items
     */
    private static void registerItems() {

        GameRegistry.registerItem(ModItems.itemlighter, "Boom Stick");

        GameRegistry.registerItem(ModItems.itemSwordPhantomIron, "Phantom Sword");

        GameRegistry.registerItem(ModItems.itemLucidity, "Lucidity");

        GameRegistry.registerItem(ModItems.itemMethaneClathrate, "Methane Clathrate");

        //GameRegistry.registerItem(ModItems.itemSparklingSapling, "Sparking Sapling");
    }
    /*
     * Register Fuels
     */
    private static void registerFuels(){
        GameRegistry.registerFuelHandler(new DreamLandFuelHandler());
    }
    /*
     * Register Tile Entities
     */
    private static void registerTileEntities(){
        ClientRegistry.registerTileEntity(TileChickenAlter.class, Reference.chickenAlter, new RenderChickenAlter());
    }
    /*
     * Register NPC's er Notch Mobs
     */
    private static void registerMobs()
    {
        //************SONIC CREEPER ***********
        int entityID;
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntitySonicCreeper.class, "SonicCreeper", entityID);
        EntityRegistry.addSpawn(EntitySonicCreeper.class, 3, 1, 2, EnumCreatureType.creature, ModBiomes.LushHills);
        EntityList.addMapping(EntitySonicCreeper.class, "SonicCreeper", entityID, 0x6E4875, 0x969696);
        //************NightMare Creeper *******
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityNMCreeper.class, "NightMareCreeper", entityID);
        EntityRegistry.addSpawn(EntityNMCreeper.class, 3, 1, 2, EnumCreatureType.monster, ModBiomes.TreacherousHills);
        EntityList.addMapping(EntityNMCreeper.class, "NightMareCreeper", entityID, 0x262425, 0x780510);
        //*************Ibong Adarna************



        //************Phoenix******************
        entityID = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityPhoenix.class, "Phoenix", entityID);
        EntityList.addMapping(EntityPhoenix.class, "Phoenix", entityID, 0x780510, 0xD16B04);
        //************Fire_Fish****************
        
    }
    /*
     * Register Villagers and Village Components
     */
    private static void registerVillagers(){
        VillagerRegistry.instance().registerVillagerType(Config.shamanVillagerID, Reference.mobTexture +"shamanVillager.png");
        VillagerRegistry.instance().registerVillageTradeHandler(Config.shamanVillagerID,  new VillagerTrades());
    }
    /*
     * Register Dimensions
     */
    private static void registerDimension() {

        DimensionManager.registerProviderType(Config.dimensionID, DreamLandWorldProvider.class, true);//boolean = to keep Dim loaded or not

        DimensionManager.registerDimension(Config.dimensionID, Config.dimensionID);

        DimensionManager.registerProviderType(Config.dimensionNMID, NightMareWorldProvider.class, true);//boolean = to keep Dim loaded or not

        DimensionManager.registerDimension(Config.dimensionNMID, Config.dimensionNMID);
    }
    private static void registerEvents(){

        MinecraftForge.EVENT_BUS.register(new EventHookHandler());

        MinecraftForge.EVENT_BUS.register(new DreamLand_EventBonemeal());
    }
    /** Harvest Levels : 3 = diamond, 2 = iron, 1 = stone and 0 = wood/gold*/
    public static void addHarvestLevels(){

        MinecraftForge.setBlockHarvestLevel(ModBlocks.portalObsidian, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamStone, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.nmStone, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamGrass, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamDirt, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.nmGrass, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.nmDirt, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamCobble, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.nmCobble, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.bloodStairs, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamStairs, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.nmStairs, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamStones, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamWall, "pickaxe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamFalling, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamLogs, "axe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.nmLogs, "axe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamPlanks, "axe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.dreamFence, "axe", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.oreMethaneClathrate, "shovel", 0);
        MinecraftForge.setBlockHarvestLevel(ModBlocks.oreBlocks, "pickAxe", 2);
    }
}