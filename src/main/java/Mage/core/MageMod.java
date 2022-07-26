package Mage.core;

import Mage.cards.*;
import Mage.characters.Mage;
import Mage.helps.ModHelper;
import Mage.pathes.AbstractCardEnum;
import Mage.pathes.ThmodClassEnum;
import Mage.relics.MyRelic;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class MageMod implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber {
    // 人物选择界面按钮的图片
    public static final  String MY_CHARACTER_BUTTON  = ModHelper.MakeAssetPath("img/charSelect/RDButton.png");
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = ModHelper.MakeAssetPath("img/charSelect/RDPortrait.png");
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = ModHelper.MakeAssetPath("img/512/bg_attack_512.png");
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = ModHelper.MakeAssetPath("img/512/bg_power_512.png");
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = ModHelper.MakeAssetPath("img/512/bg_skill_512.png");
    private static final String ENERGY_ORB_CC = ModHelper.MakeAssetPath("img/512/SELESOrb.png");
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = ModHelper.MakeAssetPath("img/512/small_orb.png");
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = ModHelper.MakeAssetPath("img/1024/bg_attack_rd.png");
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = ModHelper.MakeAssetPath("img/1024/bg_power_rd.png");
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = ModHelper.MakeAssetPath("img/1024/bg_skill_rd.png");
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = ModHelper.MakeAssetPath("img/1024/SELESOrb.png");
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = ModHelper.MakeAssetPath("img/UI/energyOrb.png");

    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    public MageMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe((ISubscriber) this);
        BaseMod.addColor(AbstractCardEnum.MAGE_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER,
                BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENERGY_ORB_CC, BG_ATTACK_1024, BG_SKILL_1024,BG_POWER_1024,
                BIG_ORB, ENEYGY_ORB);

    }

    public static void initialize() {
        new MageMod();

        BaseMod.addColor(AbstractCardEnum.MAGE_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER,
                BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, SMALL_ORB,
                BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, ENEYGY_ORB);

    }

    // basemod注册mod卡牌调用该方法
    @Override
    public void receiveEditCards() {
        BaseMod.logger.info("===添加卡牌===");
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new ArcaneWisdom());
        BaseMod.addCard(new FireBall());
        BaseMod.addCard(new Firestorm());
        BaseMod.addCard(new FireWard());
        BaseMod.addCard(new FlamePillar());
        BaseMod.addCard(new FrostBolt());
        BaseMod.addCard(new GreenMourning());
        BaseMod.addCard(new IceProtection());
        BaseMod.logger.info("===添加卡牌完成===");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.logger.info("==添加角色==");
        //添加角色到MOD中
        BaseMod.addCharacter((AbstractPlayer)new Mage("Mage"), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, ThmodClassEnum.Mage_Class);
        BaseMod.logger.info("==完成添加角色==");

    }

    @Override
    public void receiveEditKeywords() {

    }

    @Override
    public void receiveEditRelics() {
        BaseMod.logger.info("===正在添加遗物===");
        BaseMod.addRelic(new MyRelic(), RelicType.SHARED); // SHARED表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        BaseMod.logger.info("===添加遗物完成===");
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.logger.info("===正在添加本地化===");
        //读取遗物，卡牌，能力，药水，事件的JSON文本
        String relic="", character="", card="", power="", language="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            language = "ZHS";
        } else {
            //其他语言配置的JSON
            language = "EN";
        }
        character = ModHelper.MakeAssetPath("localization/"+language +"/ThMod_characters.json");
        card = ModHelper.MakeAssetPath("localization/"+language +"/ThMod_cards.json");
        relic = ModHelper.MakeAssetPath("localization/"+language+"/ThMod_relics.json");
        power = ModHelper.MakeAssetPath("localization/"+language+"/ThMod_powers.json");

        String characterStrings = Gdx.files.internal(character).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, characterStrings);

        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        BaseMod.logger.info("===添加本地化成功===");
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePowersModified() {

    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {

    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostDungeonInitialize() {

    }

    @Override
    public void receivePostEnergyRecharge() {

    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostInitialize() {

    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {

    }

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {

    }
}
