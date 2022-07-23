package Mage.cards;

import Mage.helps.ModHelper;
import Mage.pathes.AbstractCardEnum;
import Mage.powers.FrostBoltPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


/**
* author: catrefine
* desc: 寒冰箭，造成伤害，冰冻对方一回合, 不能攻击，可以防御
 */
public class FrostBolt extends CustomCard {
    // 从json中初始化Strike
    public static final String ID = ModHelper.MakePath("FrostBolt");
    private static final CardStrings cardstr = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String Name =cardstr.NAME;
    private static final String ImgPath = ModHelper.MakeAssetPath("img/cards/Strike.png");
    private static final int Cost = 1;
    private static final String Desc =cardstr.DESCRIPTION;
    private static final CardType CType = CardType.SKILL;
    private static final CardColor CColor = AbstractCardEnum.MAGE_COLOR;
    private static final CardRarity CRarity = CardRarity.BASIC;
    private static final CardTarget CTarget = CardTarget.ENEMY;
    private static final int AttackDmg = 9;
    private static final int magicNum= 10;

    public FrostBolt() {
        super(ID, Name, ImgPath, Cost, Desc,
                CType, CColor, CRarity, CTarget);

        this.damage = this.baseDamage = AttackDmg;

        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        // 升级调用的方法
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
        }
    }

    @Override
    public void use(AbstractPlayer ap, AbstractMonster am) {
//        AbstractDungeon.actionManager.addToBottom(
//                new DamageAction( am,
//                        new DamageInfo(ap,damage, DamageInfo.DamageType.NORMAL)
//                ));
        this.addToBot(new DamageAction(am,
                new DamageInfo(ap,damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)ap, (AbstractCreature)ap,
                (AbstractPower)new FrostBoltPower(am,this.magicNum)));
    }
}
