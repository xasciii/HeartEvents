package pro.heartnet.heartevents.listeners;

import io.papermc.paper.event.player.PlayerFlowerPotManipulateEvent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pro.heartnet.heartstaff.utils.other.CC;

public class BuildListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if((event.getBlock().getType() == Material.BEDROCK || event.getBlock().getType() == Material.BARRIER) && !player.isOp()){
            event.setCancelled(true);
            return;
        }
        if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if((event.getBlock().getType() == Material.BEDROCK || event.getBlock().getType() == Material.BARRIER) && !player.isOp()){
            event.setCancelled(true);
            return;
        }
        if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                if(isInteractable(event.getClickedBlock())){
                    event.setCancelled(true);
                }
            }
        } else if(event.getAction() == Action.PHYSICAL){
            if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPotManipulate(PlayerFlowerPotManipulateEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onStandManipulate(PlayerArmorStandManipulateEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onGrassGrowth(BlockGrowEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onLeafsDecay(LeavesDecayEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockFade(BlockFadeEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockForm(BlockFormEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockBurn(BlockBurnEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event){
        if(event.getBlock().getType().name().endsWith("_DOOR")) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void onCraft(CraftItemEvent event){
        event.setCancelled(true);
        event.getWhoClicked().sendMessage(CC.translate("&cCrafting is disabled on this server."));
    }
    @EventHandler
    public void onFish(PlayerFishEvent event){
        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH || event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY){
            event.setCancelled(true);
            event.getPlayer().sendMessage(CC.translate("&cFishing is disabled on this server."));
        }
    }

    public static boolean isInteractable(Block block) {
        if(block == null) return false;
        Material type = block.getType();
        //boolean interactable = type.isInteractable();
        //if (!interactable) return false;

        String str = type.name();
        return (str.endsWith("_TRAPDOOR")
                || str.endsWith("_PRESSURE_PLATE")
                || str.endsWith("_BUTTON")
                || str.endsWith("_SIGN")
                || str.endsWith("_STAND")
                || str.endsWith("ITEM_FRAME")
                || str.endsWith("FURNACE")
                || str.endsWith("CHEST")
                || str.endsWith("TABLE")
                || str.equals("FLOWER_POT")
                || str.equals("LEVER")
                || str.equals("SMOKER")
                || str.equals("REDSTONE_DUST")
                || str.equals("BARREL")
                || str.equals("ANVIL")
                || str.equals("HOPPER")
                || str.equals("REPEATER")
                || str.equals("COMPARATOR")
                || str.equals("NOTE_BLOCK")
                || str.equals("JUKEBOX")
                || str.equals("DROPPER")
                || str.equals("DISPENSER")
                || str.equals("BELL")
                || str.equals("BEACON")
                || str.equals("LECTERN")
        );
    }
}
