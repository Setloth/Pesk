package ignore;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class ExprTeamPrefix extends SimplePropertyExpression<Team, String> {

    static {
        register(ExprTeamPrefix.class, String.class, "[team] prefix", "teams");
    }

    @Override
    protected String getPropertyName() {
        return "team prefix";
    }

    @Nullable
    @Override
    public String convert(Team team) {
        return ((TextComponent)team.prefix()).content();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return new Class[] {String.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final Changer.ChangeMode mode) {
        if (delta == null) {
            for (final Team t : getExpr().getArray(e)) {
                t.prefix(null);
            }
        } else {
            final String name = (String) delta[0];
            for (final Team t : getExpr().getArray(e)) {
                t.prefix(Component.text(name));
            }
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
