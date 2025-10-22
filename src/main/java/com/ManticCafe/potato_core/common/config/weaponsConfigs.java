package com.ManticCafe.potato_core.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class weaponsConfigs {

    public final ForgeConfigSpec.IntValue l1_projectile_damage; //l1投掷物伤害
    public final ForgeConfigSpec.IntValue l1_cd; //l1投掷物CD

    public final ForgeConfigSpec.IntValue l2_projectile_damage;
    public final ForgeConfigSpec.IntValue l2_cd;

    public final ForgeConfigSpec.IntValue l3_projectile_damage;
    public final ForgeConfigSpec.IntValue l3_cd;

    public final ForgeConfigSpec.IntValue l4_projectile_damage;
    public final ForgeConfigSpec.IntValue l4_cd;

    public final ForgeConfigSpec.IntValue l5_projectile_damage;
    public final ForgeConfigSpec.IntValue l5_cd;

    public final ForgeConfigSpec.IntValue l6_projectile_damage;
    public final ForgeConfigSpec.IntValue l6_cd;

    public final ForgeConfigSpec.IntValue l7_projectile_damage;
    public final ForgeConfigSpec.IntValue l7_cd;

    public final ForgeConfigSpec.IntValue l8_projectile_damage;
    public final ForgeConfigSpec.IntValue l8_cd;

    public final ForgeConfigSpec.IntValue l9_projectile_damage;
    public final ForgeConfigSpec.IntValue l9_cd;

    public final ForgeConfigSpec.IntValue r_projectile_damage;
    public final ForgeConfigSpec.IntValue r_cd;
    public final ForgeConfigSpec.BooleanValue r_c;

    public weaponsConfigs(ForgeConfigSpec.Builder builder) {

        builder.comment("武器配置");
//        builder.pop();

        builder.push("最终之剑：");
        l1_projectile_damage = builder
                .comment("最终之剑投掷物伤害:（0 ~ 2147483647,默认：12）")
                .defineInRange("projectile_damage",12,0,2147483647);
        l1_cd = builder
                .comment("最终之剑投掷物冷却:（0 ~ 2147483647,默认：80）")
                .defineInRange("cd",80,0,2147483647);
        builder.pop();

        builder.push("最终之剑(二级):");
        l2_projectile_damage = builder
                .comment("最终之剑(二级)投掷物伤害:（0 ~ 2147483647,默认：24）")
                .defineInRange("projectile_damage",24,0,2147483647);
        l2_cd = builder
                .comment("最终之剑(二级)投掷物冷却:（0 ~ 2147483647,默认：70）")
                .defineInRange("cd",70,0,2147483647);
        builder.pop();

        builder.push("最终之剑(三级):");
        l3_projectile_damage = builder
                .comment("最终之剑(三级)投掷物伤害:（0 ~ 2147483647,默认：36）")
                .defineInRange("projectile_damage",36,0,2147483647);
        l3_cd = builder
                .comment("最终之剑(三级)投掷物冷却:（0 ~ 2147483647,默认：60）")
                .defineInRange("cd",60,0,2147483647);
        builder.pop();

        builder.push("最终之剑(四级):");
        l4_projectile_damage = builder
                .comment("最终之剑(四级)投掷物伤害:（0 ~ 2147483647,默认：42）")
                .defineInRange("projectile_damage",42,0,2147483647);
        l4_cd = builder
                .comment("最终之剑(四级)投掷物冷却:（0 ~ 2147483647,默认：50）")
                .defineInRange("cd",50,0,2147483647);
        builder.pop();

        builder.push("最终之剑(五级):");
        l5_projectile_damage = builder
                .comment("最终之剑(五级)投掷物伤害:（0 ~ 2147483647,默认：45）")
                .defineInRange("projectile_damage",45,0,2147483647);
        l5_cd = builder
                .comment("最终之剑(五级)投掷物冷却:（0 ~ 2147483647,默认：45）")
                .defineInRange("cd",45,0,2147483647);
        builder.pop();

        builder.push("最终之剑(六级):");
        l6_projectile_damage = builder
                .comment("最终之剑(六级)投掷物伤害:（0 ~ 2147483647,默认：49）")
                .defineInRange("projectile_damage",49,0,2147483647);
        l6_cd = builder
                .comment("最终之剑(六级)投掷物冷却:（0 ~ 2147483647,默认：40）")
                .defineInRange("cd",40,0,2147483647);
        builder.pop();

        builder.push("最终之剑(七级):");
        l7_projectile_damage = builder
                .comment("最终之剑(七级)投掷物伤害:（0 ~ 2147483647,默认：57）")
                .defineInRange("projectile_damage",57,0,2147483647);
        l7_cd = builder
                .comment("最终之剑(七级)投掷物冷却:（0 ~ 2147483647,默认：30）")
                .defineInRange("cd",30,0,2147483647);
        builder.pop();

        builder.push("最终之剑(八级):");
        l8_projectile_damage = builder
                .comment("最终之剑(八级)投掷物伤害:（0 ~ 2147483647,默认：64）")
                .defineInRange("projectile_damage",64,0,2147483647);
        l8_cd = builder
                .comment("最终之剑(八级)投掷物冷却:（0 ~ 2147483647,默认：20）")
                .defineInRange("cd",20,0,2147483647);
        builder.pop();

        builder.push("最终之剑(终极?):");
        l9_projectile_damage = builder
                .comment("最终之剑(终极?)投掷物伤害:（0 ~ 2147483647,默认：128）")
                .defineInRange("projectile_damage",128,0,2147483647);
        l9_cd = builder
                .comment("最终之剑(终极?)投掷物冷却:（0 ~ 2147483647,默认：5）")
                .defineInRange("cd",5,0,2147483647);
        builder.pop();

        builder.push("土豆之剑:");
        r_projectile_damage = builder
                .comment("土豆之剑投掷物伤害:（0 ~ 2147483647,默认：1024）")
                .defineInRange("projectile_damage",1024,0,2147483647);
        r_cd = builder
                .comment("土豆之剑投掷物冷却:（0 ~ 2147483647,默认：1）")
                .defineInRange("cd",1,0,2147483647);
        r_c = builder
                .comment("是否启用挖掘功能：(Boolean,默认true)")
                        .define("Destruction_function",true);
        builder.pop();
    }

}
