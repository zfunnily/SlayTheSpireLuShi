package Mage.characters;

import Mage.helps.ModHelper;
import Mage.pathes.ThmodClassEnum;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import Mage.pathes.AbstractCardEnum;
import Mage.relics.MyRelic;

import java.util.ArrayList;

public class Mage extends CustomPlayer {
    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String SELES_SHOULDER_2 = "img/char_Seles/shoulder2.png";
    private static final String SELES_SHOULDER_1 = "img/char_Seles/shoulder1.png";
    private static final String SELES_CORPSE = "img/char_Seles/fallen.png";
    private static final String SELES_STAND = "img/char_Seles/Seles.png";
    //各种素材，不是很懂
    private static final String[] ORB_TEXTURES = new String[] { "img/UI_Seles/EPanel/layer5.png", "img/UI_Seles/EPanel/layer4.png", "img/UI_Seles/EPanel/layer3.png", "img/UI_Seles/EPanel/layer2.png", "img/UI_Seles/EPanel/layer1.png", "img/UI_Seles/EPanel/layer0.png", "img/UI_Seles/EPanel/layer5d.png", "img/UI_Seles/EPanel/layer4d.png", "img/UI_Seles/EPanel/layer3d.png", "img/UI_Seles/EPanel/layer2d.png", "img/UI_Seles/EPanel/layer1d.png" };
    private static final String ORB_VFX = "img/UI_Seles/energyBlueVFX.png";
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 75; // 当前血量
    private static final int MAX_HP = 75; // 最大血量
    private static final int STARTING_GOLD = 99; // 初始携带金币
    private static final int HAND_SIZE = 0; // 初始充能球栏位
    private static final int ASCENSION_MAX_HP_LOSS = 5; // 每回合抽牌数量
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    // 人物的本地化文本，如卡牌的本地化文本一样，如何书写见下
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ModHelper.MakeCharacterPath("Gem"));

    public Mage(String name) {
        super(name, ThmodClassEnum.Mage_Class, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null, (String)null);

        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;

        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        initializeClass(SELES_STAND, SELES_SHOULDER_2, SELES_SHOULDER_1, SELES_CORPSE,
                this.getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
    }

    @Override // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(ModHelper.MakePath("Strike"));
        retVal.add(ModHelper.MakePath("Strike"));
        retVal.add(ModHelper.MakePath("Strike"));
        retVal.add(ModHelper.MakePath("Strike"));
        retVal.add(ModHelper.MakePath("Strike"));
        retVal.add(ModHelper.MakePath("Defend"));
        retVal.add(ModHelper.MakePath("Defend"));
        retVal.add(ModHelper.MakePath("Defend"));
        retVal.add(ModHelper.MakePath("Defend"));
        retVal.add(ModHelper.MakePath("Defend"));
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(ModHelper.MakePath("MyRelic"));
//        retVal.add(Vajra.ID);
        UnlockTracker.markRelicAsSeen(MyRelic.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选英雄界面的文字描述
        String title="";
        String flavor="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "吉安娜";
            flavor = "初出茅庐的法师， NL 每天要面对各种数不清的法术学习和除怪任务。";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            //当设定为台湾，title和flavor为繁体描述
        } else {
            //其他用英文替代
        }

        return new CharSelectInfo(title, flavor,
                STARTING_HP,
                MAX_HP,HAND_SIZE,
                STARTING_GOLD,
                ASCENSION_MAX_HP_LOSS,
                this, //
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(),
                false);
    }


    @Override
    public String getTitle(PlayerClass playerClass) {
        //应该是进游戏后左上角的角色名
        String title="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "吉安娜";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "吉安娜";
        } else {
            title = "GANNA";
        }

        return title;
    }

    @Override

    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return AbstractCardEnum.MAGE_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return SILVER;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }
    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        String char_name;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "莎莉丝";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            char_name = "莎莉絲";
        } else {
            char_name = "Seles";
        }
        return char_name;
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new Mage(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[15];
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.BLUNT_LIGHT, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_LIGHT, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_LIGHT };
    }

    @Override
    public String getVampireText() {

        return null;
    }
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }
}
