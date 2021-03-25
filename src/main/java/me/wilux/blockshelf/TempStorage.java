package me.wilux.blockshelf;

import me.wilux.blockshelf.nms_hacks.EntityFixedLeashTarget;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TempStorage {
    public static HashMap<Player, EntityFixedLeashTarget> leashSource = new HashMap<>();
}
