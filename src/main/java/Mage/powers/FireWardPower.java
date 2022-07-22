package Mage.powers;

import Mage.helps.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FireWardPower extends AbstractPower {
    private static final Logger logger = LogManager.getLogger(FireWardPower.class.getName());
    public static final String POWER_ID = ModHelper.MakePath("FireWard") ;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FireWardPower(AbstractCreature owner, int thornsDamage) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = thornsDamage;
        updateDescription();
//        loadRegion("fireWard");
    }


    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null
           && info.type != DamageInfo.DamageType.THORNS
           && info.type != DamageInfo.DamageType.HP_LOSS
           && info.owner != this.owner) {
            flash();
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        }
        return damageAmount;
    }

    public void atStartOfTurn() {
        this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature) AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, "Flame Barrier"));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
