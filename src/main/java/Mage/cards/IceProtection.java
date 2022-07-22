package Mage.cards;

import Mage.helps.ModHelper;
import Mage.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * author: catrefine
 * Mystery
 * desc: 寒冰护体
 */
public class IceProtection extends CustomCard {
    // 从json中初始化Strike
    private static final String ID = ModHelper.MakePath("IceProtection");
    private static final CardStrings cardstr = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String Name =cardstr.NAME;
    private static final String ImgPath = ModHelper.MakeAssetPath("img/cards/Defend.png");
    private static final int Cost = 1;
    private static final String Desc =cardstr.DESCRIPTION;
    private static final AbstractCard.CardType CType = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardColor CColor = AbstractCardEnum.MAGE_COLOR;
    private static final AbstractCard.CardRarity CRarity = AbstractCard.CardRarity.BASIC;
    private static final AbstractCard.CardTarget CTarget = AbstractCard.CardTarget.SELF;

    public IceProtection() {
        super(ID, Name, ImgPath, Cost, Desc,
                CType, CColor, CRarity, CTarget);

        // 添加基础防御
        this.baseBlock = this.block = 8;
        // 添加STARTER_STRIKE（基础防御）
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
    }

    @Override
    public void upgrade() {
        // 升级调用的方法
        if (!this.upgraded) {
            this.upgradeName(); // 卡牌名字变为绿色并添加“+”，且标为升级过的卡牌，之后不能再升级。
            this.upgradeBlock(4); // 将该卡牌的防御提高3点。
        }
    }

    @Override
    public void use(AbstractPlayer ap, AbstractMonster am) {
        this.addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)ap,
                (AbstractCreature)ap, this.baseBlock));

    }
}
