package Mage.powers;

import Mage.actions.GreenMourningAction;
import Mage.helps.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GreenMourningPower extends AbstractPower {
    private static final Logger logger = LogManager.getLogger(GreenMourningPower.class.getName());
    public static final String POWER_ID = ModHelper.MakePath("GreenMourning") ;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GreenMourningPower(AbstractCreature owner, int thornsDamage) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = thornsDamage;
        updateDescription();
//        loadRegion("fireWard");
    }

    public void atStartOfTurn() {
        addToBot((AbstractGameAction)new GreenMourningAction(this.amount));
//        flash();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
