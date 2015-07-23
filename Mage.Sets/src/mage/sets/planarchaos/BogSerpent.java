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
package mage.sets.planarchaos;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.ControlsPermanentsControllerTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.SacrificeSourceEffect;
import mage.abilities.effects.common.combat.CantAttackUnlessDefenderControllsPermanent;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.filter.Filter;
import mage.filter.common.FilterLandPermanent;

/**
 *
 * @author fireshoes
 */
public class BogSerpent extends CardImpl {

    public BogSerpent(UUID ownerId) {
        super(ownerId, 84, "Bog Serpent", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{5}{B}");
        this.expansionSetCode = "PLC";
        this.subtype.add("Serpent");
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        // Bog Serpent can't attack unless defending player controls a Swamp.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new CantAttackUnlessDefenderControllsPermanent(new FilterLandPermanent("Swamp", "a Swamp"))));

        // When you control no Swamps, sacrifice Bog Serpent.
        this.addAbility(new ControlsPermanentsControllerTriggeredAbility(
                new FilterLandPermanent("Swamp", "no Swamps"), Filter.ComparisonType.Equal, 0,
                new SacrificeSourceEffect()));
    }

    public BogSerpent(final BogSerpent card) {
        super(card);
    }

    @Override
    public BogSerpent copy() {
        return new BogSerpent(this);
    }
}