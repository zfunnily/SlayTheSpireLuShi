package Mage.powers;

import Mage.helps.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IceProtectionPower extends AbstractPower {
    private static final Logger logger = LogManager.getLogger(IceProtectionPower.class.getName());
    public static final String POWER_ID = ModHelper.MakePath("IceProtection") ;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IceProtectionPower(AbstractCreature owner, int block) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = block;
        updateDescription();
//        loadRegion("fireWard");
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            this.addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)target,
                    (AbstractCreature)this.owner, this.amount));

            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature) this.owner, (AbstractCreature)this.owner, "IceProtection"));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
