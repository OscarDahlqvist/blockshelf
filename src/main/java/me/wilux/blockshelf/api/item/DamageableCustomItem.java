package me.wilux.blockshelf.api.item;

import me.wilux.blockshelf.main.Blockshelf;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DamageableCustomItem extends CustomItem {
    private int maxDurability;
    private int baseMaterialMaxDurability;

    public DamageableCustomItem(String name, String modelPath, String displayName, Material baseMat, int maxDurability) {
        super(name, modelPath, displayName, baseMat);

        this.maxDurability = maxDurability;
        this.baseMaterialMaxDurability = baseMat.getMaxDurability();

        Blockshelf.getLog().warning(maxDurability+":"+baseMaterialMaxDurability);
    }

    public void setMaxDurability(int maxDurability) { this.maxDurability = maxDurability; }
    public int getMaxDurability() { return maxDurability; }

    public Sound getBreakSound(){
        return null;
    }

    public boolean whenNoDurabilityLeft(ItemStack itemInstance){
        //Play breaksound
        itemInstance.setAmount(0);
        return true;
    }

    public int repairItem(ItemStack itemInstance, int i){
        return damageItem(itemInstance, -i);
    }

    public int damageItem(ItemStack itemInstance, int i){
        ItemMeta meta = itemInstance.getItemMeta();

        //set custom meta damage
        PersistentDataContainer exMeta = meta.getPersistentDataContainer();
        NamespacedKey cmDamageKey = new NamespacedKey(Blockshelf.getInstance(), "cmDamage");
        int cmDamage = exMeta.getOrDefault(cmDamageKey, PersistentDataType.INTEGER, 0);
        cmDamage += i;
        exMeta.set(cmDamageKey, PersistentDataType.INTEGER, cmDamage);

        //set real damage
        Damageable dmgMeta = (Damageable) meta;
        float damagePercentage = (float)cmDamage/(float)maxDurability;
        int realDamage = (int)(damagePercentage*(float)baseMaterialMaxDurability);
        realDamage = Math.max(0,realDamage);
        dmgMeta.setDamage(realDamage);
        itemInstance.setItemMeta((ItemMeta) dmgMeta);

        if(cmDamage > maxDurability){
            whenNoDurabilityLeft(itemInstance);
        }

        return cmDamage;
    }
}
