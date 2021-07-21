package ignore;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class ExprTeam extends SimplePropertyExpression<LivingEntity, Team> {

    static {
        register(ExprTeam.class, Team.class, "[scoreboard] team", "livingentities");
    }

    @Override
    protected String getPropertyName() {
        return "team";
    }

    @Nullable
    @Override
    public Team convert(LivingEntity e) {
        return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(e.getName());
    }

    @Override
    public Class<? extends Team> getReturnType() {
        return Team.class;
    }
}
