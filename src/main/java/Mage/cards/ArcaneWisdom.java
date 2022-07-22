package Mage.cards;

import Mage.helps.ModHelper;
import Mage.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.FastDrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArcaneWisdom extends CustomCard {
    private static final String ID = ModHelper.MakePath("ArcaneWisdom");
    private static final CardStrings cardstr = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String Name =cardstr.NAME;
    private static final String ImgPath = ModHelper.MakeAssetPath("img/cards/Strike.png");
    private static final int Cost = 1;
    private static final String Desc =cardstr.DESCRIPTION;
    private static final CardType CType = CardType.SKILL;
    private static final CardColor CColor = AbstractCardEnum.MAGE_COLOR;
    private static final CardRarity CRarity = CardRarity.BASIC;
    private static final CardTarget CTarget = CardTarget.SELF;
    private static final int AttackDmg = 0;
    private static final int MagicNum = 2;
    private static final int UpNum = 3;

    public ArcaneWisdom() {
        super(ID, Name, ImgPath, Cost, Desc,
                CType, CColor, CRarity, CTarget);

        this.magicNumber = this.baseMagicNumber = MagicNum;
        this.tags.add(CardTags.EMPTY);
    }

    @Override
    public void upgrade() {
        // 升级调用的方法
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeMagicNumber(UpNum);
        }
    }

    @Override
    public void use(AbstractPlayer ap, AbstractMonster am) {
        AbstractDungeon.actionManager.addToBottom( new FastDrawCardAction( ap, this.magicNumber));
    }

}
