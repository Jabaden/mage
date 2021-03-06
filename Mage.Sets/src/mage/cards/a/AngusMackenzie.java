/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.ActivateIfConditionActivatedAbility;
import mage.abilities.condition.Condition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.PreventAllDamageByAllPermanentsEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;

import java.util.UUID;

/**
 *
 * @author shieldal
 */
public class AngusMackenzie extends CardImpl {

    public AngusMackenzie(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{G}{W}{U}");
        addSuperType(SuperType.LEGENDARY);
        this.subtype.add("Human");
        this.subtype.add("Cleric");

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        Effect effect = new PreventAllDamageByAllPermanentsEffect(Duration.EndOfTurn, true);
        effect.setText("Prevent all combat damage that would be dealt this turn");
        // {G}{W}{U}, {tap}: Prevent all combat damage that would be dealt this turn. Activate this ability only before the combat damage step.
        Ability ability = new ActivateIfConditionActivatedAbility(
                Zone.BATTLEFIELD, 
                effect,
                new ManaCostsImpl("{G}{W}{U}"), 
                BeforeCombatDamageCondition.getInstance()
        );
        ability.addCost(new TapSourceCost());
        this.addAbility(ability);
    }

    public AngusMackenzie(final AngusMackenzie card) {
        super(card);
    }

    @Override
    public AngusMackenzie copy() {
        return new AngusMackenzie(this);
    }
}

class BeforeCombatDamageCondition implements Condition {
    private static final BeforeCombatDamageCondition instance = new BeforeCombatDamageCondition();

    public static Condition getInstance() {
        return instance;
    }

    @Override
    public boolean apply(Game game, Ability source) {
            PhaseStep phaseStep = game.getStep().getType();
            if(phaseStep.getIndex() < PhaseStep.FIRST_COMBAT_DAMAGE.getIndex()) {
                return true;
            }
        return false;
    }

    @Override
    public String toString() {
	return "before the combat damage step";
    }
}