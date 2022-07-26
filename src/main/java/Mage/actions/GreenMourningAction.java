package Mage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
public class GreenMourningAction extends AbstractGameAction {
    private int discountAmount;
    public GreenMourningAction(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void update() {
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
        if (c.selfRetain || c.retain) {
            c.modifyCostForCombat(-this.discountAmount);
        }
    }
        this.isDone = true;
    }
}
