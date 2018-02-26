package net.teamfruit.tcpatch;

import javax.annotation.Nonnull;

import net.minecraftforge.common.config.Configuration;
import net.teamfruit.tcpatch.ConfigBase.ConfigProperty;

public class Configs {
	private ConfigBase config;

	public final ConfigProperty<Integer> idPotionFluxTaint;
	public final ConfigProperty<Integer> idPotionVisExhaust;
	public final ConfigProperty<Integer> idPotionInfectiousVisExhaust;
	public final ConfigProperty<Integer> idPotionUnnaturalHunger;
	public final ConfigProperty<Integer> idPotionWarpWard;
	public final ConfigProperty<Integer> idPotionDeathGaze;
	public final ConfigProperty<Integer> idPotionBlurredVision;
	public final ConfigProperty<Integer> idPotionSunScorned;
	public final ConfigProperty<Integer> idPotionThaumarhia;

	public Configs(final @Nonnull Configuration cfg) {
		// init static config
		this.config = new ConfigBase(cfg);

		cfg.getCategory("potion_ids").setLanguageKey("tcpatch.config.potionids").setComment("ID of potions");
		this.idPotionFluxTaint = this.config.propertyInteger(cfg.get("potion_ids", "potion_1_flux_taint", -2, "Potion id of PotionFluxTaint, order=1 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_flux_taint"));
		this.idPotionVisExhaust = this.config.propertyInteger(cfg.get("potion_ids", "potion_2_vis_exhaust", -2, "Potion id of PotionVisExhaust, order=2 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_vis_exhaust"));
		this.idPotionInfectiousVisExhaust = this.config.propertyInteger(cfg.get("potion_ids", "potion_3_infectious_vis_exhaust", -2, "Potion id of PotionInfectiousVisExhaust, order=3 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_infectious_vis_exhaust"));
		this.idPotionUnnaturalHunger = this.config.propertyInteger(cfg.get("potion_ids", "potion_4_unnatural_hunger", -2, "Potion id of PotionUnnaturalHunger, order=4 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_unnatural_hunger"));
		this.idPotionWarpWard = this.config.propertyInteger(cfg.get("potion_ids", "potion_5_warp_ward", -2, "Potion id of PotionWarpWard, order=5 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_warp_ward"));
		this.idPotionDeathGaze = this.config.propertyInteger(cfg.get("potion_ids", "potion_6_death_gaze", -2, "Potion id of PotionDeathGaze, order=6 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_death_gaze"));
		this.idPotionBlurredVision = this.config.propertyInteger(cfg.get("potion_ids", "potion_7_blurred_vision", -2, "Potion id of PotionBlurredVision, order=7 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_blurred_vision"));
		this.idPotionSunScorned = this.config.propertyInteger(cfg.get("potion_ids", "potion_8_sun_scorned", -2, "Potion id of PotionSunScorned, order=8 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_sun_scorned"));
		this.idPotionThaumarhia = this.config.propertyInteger(cfg.get("potion_ids", "potion_9_thaumarhia", -2, "Potion id of PotionThaumarhia, order=9 (invalid with negative value, disabled=-1, uninitialized=-2)").setMinValue(-2).setMaxValue(127).setLanguageKey("tcpatch.config.potionids.potion_thaumarhia"));
	}

	public ConfigBase getBase() {
		return this.config;
	}

	public void save() {
		this.config.getConfig().save();
	}
}
