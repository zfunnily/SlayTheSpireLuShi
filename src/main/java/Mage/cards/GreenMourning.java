package Mage.cards;

import Mage.actions.GreenMourningAction;
import Mage.helps.ModHelper;
import Mage.pathes.AbstractCardEnum;
import Mage.powers.FrostBoltPower;
import Mage.powers.GreenMourningPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Establishment;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


/**
* author: catrefine
* desc: 基础打击牌
 */
public class GreenMourning extends CustomCard {
    // 从json中初始化Strike
    public static final String ID = ModHelper.MakePath("GreenMourning");

    private static final CardStrings cardstr = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String Name =cardstr.NAME;
    private static final String ImgPath = ModHelper.MakeAssetPath("img/cards/Defend.png");
    private static final int Cost = 3;
    private static final String Desc =cardstr.DESCRIPTION;
    private static final CardType CType = CardType.POWER;
    private static final CardColor CColor = AbstractCardEnum.MAGE_COLOR;
    private static final CardRarity CRarity = CardRarity.RARE;
    private static final CardTarget CTarget = CardTarget.SELF;

    public GreenMourning() {
        super(ID, Name, ImgPath, Cost, Desc,
                CType, CColor, CRarity, CTarget);
        this.magicNumber= this.baseMagicNumber= 1;
    }

    @Override
    public void upgrade() {
        // 升级调用的方法
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeMagicNumber(3);
        }
    }

    @Override
    public void use(AbstractPlayer ap, AbstractMonster am) {
//       this.addToBot((AbstractGameAction)new GreenMourningAction(this.magicNumber));
       this.addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature)ap, (AbstractCreature)ap,
                (AbstractPower)new GreenMourningPower(ap,this.magicNumber), this.magicNumber));

    }
    public AbstractCard makeCopy() {
        return new GreenMourning();
    }
}
