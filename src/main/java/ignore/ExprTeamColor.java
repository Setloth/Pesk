package ignore;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class ExprTeamColor extends SimplePropertyExpression<Team, ChatColor> {

    static {
        register(ExprTeamColor.class, ChatColor.class, "team color", "teams");
    }

    @Override
    protected String getPropertyName() {
        return "team color";
    }

    @Nullable
    @Override
    public ChatColor convert(Team team) {
        return team.getColor();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return new Class[] {ChatColor.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final Changer.ChangeMode mode) {


        if (delta == null) {
            for (final Team t : getExpr().getArray(e)) {
                t.setColor(null);
            }
        } else {
            final @NonNull ChatColor color = ((ChatColor)delta[0]);
            for (final Team t : getExpr().getArray(e)) {
                t.setColor(color);
            }
        }
    }

    @Override
    public Class<? extends ChatColor> getReturnType() {
        return ChatColor.class;
    }
}
