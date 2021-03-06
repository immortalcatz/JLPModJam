package dreamLand;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.VillagerRegistry;
import dreamLand.blocks.ModBlocks;
import dreamLand.compatibility.DreamLandCompatibility;
import dreamLand.items.ModItems;
import dreamLand.sided.CommonProxy;
import dreamLand.utils.Reference;
import dreamLand.utils.Config;
import dreamLand.utils.Dictionary;
import dreamLand.utils.DreamLandTabs;
import dreamLand.utils.Registry;
import dreamLand.utils.handlers.LanguageHandler;
import dreamLand.world.biome.ModBiomes;
import dreamLand.world.village.VillagerTrades;

@Mod(modid = Reference.modID, name = Reference.modName, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class DreamLand {

	@Instance(Reference.modID)
    public static DreamLand instance;

    @SidedProxy(clientSide = Reference.clientProxy, serverSide = Reference.serverProxy)
    public static CommonProxy proxy;

    public static EnumArmorMaterial armorMalumnite = EnumHelper.addArmorMaterial("PhantomIron", 29,
            new int[] { 2, 7, 5, 3 }, 9);
    
    public static EnumArmorMaterial armorSolarium = EnumHelper.addArmorMaterial("PhantomIron", 29,
            new int[] { 2, 7, 5, 3 }, 9);

    public static EnumToolMaterial toolMalumnite = EnumHelper.addToolMaterial("PhantomIron", 3,
            2000, 12.0F, 9, 25);
    
    public static EnumToolMaterial toolSolarium = EnumHelper.addToolMaterial("PhantomIron", 3,
            2000, 12.0F, 9, 25);

    public static dreamLand.world.terrain.MapGenCavesDL worldGen = new dreamLand.world.terrain.MapGenCavesDL();

    // Declares a new Creative Tab
    public static CreativeTabs tabDreamLand = new DreamLandTabs(CreativeTabs.getNextID(), Reference.tabDreamLand);

	//private IWorldGenerator MapGenCavesDL;

    @PreInit
    public void preInit(FMLPreInitializationEvent evt) {

        // Loads the Configuration File
        Config.init(evt);

        // Creates the Items
        ModItems.init();
        
        // Creates the Blocks
        ModBlocks.init();
        
        //Creates the Biomes
        ModBiomes.init();
        
        // Loads Languages
        LanguageHandler.loadLanguages();

        // Makes capes available :D
        proxy.initCapes();
        
      }

    @Init
    public void init(FMLInitializationEvent evt) {

        // Does all the game registry stuff
        Registry.register();
        
        proxy.registerRenderers();
        
        // Forge Dictionary registry
        Dictionary.register();
        
        DreamLandCompatibility.init();

    }

    @PostInit
    public void postInit(FMLPostInitializationEvent evt) {
        // TODO stuff
    }
}
