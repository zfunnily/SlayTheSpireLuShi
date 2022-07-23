package Mage.cards;

import Mage.helps.ModHelper;
import Mage.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


/**
* author: catrefine
 * Mystery
* desc: 火焰结界
 */
public class FireWard extends CustomCard {
    private static final String ID = ModHelper.MakePath("FireWard");
    private static final CardStrings cardstr = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String Name =cardstr.NAME;
    private static final String ImgPath = ModHelper.MakeAssetPath("img/cards/Strike.png");
    private static final int Cost = 1;
    private static final String Desc =cardstr.DESCRIPTION;
    private static final CardType CType = CardType.SKILL;
    private static final CardColor CColor = AbstractCardEnum.MAGE_COLOR;
    private static final CardRarity CRarity = CardRarity.BASIC;
    private static final CardTarget CTarget = CardTarget.SELF;
    private static final int AttackDmg = 6;
    private static final int UpDmg = 3;


    public FireWard() {
        super(ID, Name, ImgPath, Cost, Desc,
                CType, CColor, CRarity, CTarget);

        // 添加基础攻击
        this.block = this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber  = AttackDmg;

        this.tags.add(CardTags.STARTER_STRIKE);
    }

    @Override
    public void upgrade() {
        // 升级调用的方法
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
//            this.upgradeDamage(UpDmg);
            this.upgradeMagicNumber(UpDmg);
        }
    }

    @Override
    public void use(AbstractPlayer ap, AbstractMonster am) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction( am,
                        new DamageInfo(ap,damage, DamageInfo.DamageType.NORMAL)
                ));
    }
}
