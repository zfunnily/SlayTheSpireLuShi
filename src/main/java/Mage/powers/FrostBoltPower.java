package Mage.powers;

import Mage.helps.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// TODO 冻结一回合，不能打出攻击, 可以使用防御
public class FrostBoltPower extends AbstractPower {
    private static final Logger logger = LogManager.getLogger(FireWardPower.class.getName());
    public static final String POWER_ID = ModHelper.MakePath("FrostBolt") ;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrostBoltPower(AbstractCreature owner, int thornsDamage) {
        this.name = powerStrings.NAME;
        this.ID = "FrostBolt";
        this.owner = owner;
        this.amount = 1;
        updateDescription();
//        loadRegion("entangle");
        this.isTurnBased = true;
        this.type = AbstractPower.PowerType.DEBUFF;
    }


    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.owner != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            return  0;
        }
        return damageAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!isPlayer) {
            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this.owner, (AbstractCreature)AbstractDungeon.player, "IceProtection"));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
