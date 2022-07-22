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

public class FlamePillar extends CustomCard {
    // 从json中初始化Strike
    private static final String ID = ModHelper.MakePath("FireBall");
    private static final CardStrings cardstr = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String Name =cardstr.NAME;
    private static final String ImgPath = ModHelper.MakeAssetPath("img/cards/Strike.png");
    private static final int Cost = 3;
    private static final String Desc =cardstr.DESCRIPTION;
    private static final CardType CType = CardType.ATTACK;
    private static final CardColor CColor = AbstractCardEnum.MAGE_COLOR;
    private static final CardRarity CRarity = CardRarity.BASIC;
    private static final CardTarget CTarget = CardTarget.ENEMY;
    private static final int AttackDmg = 24;

    public FlamePillar() {
        super(ID, Name, ImgPath, Cost, Desc,
                CType, CColor, CRarity, CTarget);

        // 添加基础攻击
        this.damage = this.baseDamage = AttackDmg;
        this.tags.add(CardTags.STARTER_STRIKE);
        // 添加STRIKE（打击）让完美打击计算这张牌
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        // 升级调用的方法
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3); // 将该卡牌的伤害提高3点。
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
