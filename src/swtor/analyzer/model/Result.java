package swtor.analyzer.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Result extends GenericMetricEntity {

	public enum ResultType {
		COMBAT, NON_COMBAT;
	}

	private long id;
	private ResultType type = ResultType.NON_COMBAT;
	private String name = "";
	private String owner = "";
	private Calendar start;
	private Calendar end;
	private long duration;

	private long lineStart;
	private long lineEnd;

	private final Map<String, Actor> actors = new HashMap<String, Actor>();
	private final Map<String, Ability> abilities = new HashMap<String, Ability>();

	public Result(long id, long lineStart) {
		this.id = id;
		this.lineStart = lineStart;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLineStart(long lineStart) {
		this.lineStart = lineStart;
	}

	public ResultType getType() {
		return type;
	}

	public void setType(ResultType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getLineCount() {
		return lineEnd - lineStart;
	}

	public long getLineStart() {
		return lineStart;
	}

	public void setLineStart(int lineStart) {
		this.lineStart = lineStart;
	}

	public long getLineEnd() {
		return lineEnd;
	}

	public void setLineEnd(long lineEnd) {
		this.lineEnd = lineEnd;
	}

	public Map<String, Actor> getActors() {
		return actors;
	}

	public Map<String, Ability> getAbilities() {
		return abilities;
	}

	public Actor getActor(String name) {
		return actors.get(name);
	}
	
	public Ability getAbility(String name) {
		return abilities.get(name);
	}

	public Actor addActor(Actor actor) {
		Actor found = actors.get(actor.getName());
		if (found == null) {
			actors.put(actor.getName(), actor);
			found = actor;
		}
		return found;
	}

	public Ability addAbility(Ability ability) {
		Ability found = abilities.get(ability.getName());
		if (found == null) {
			abilities.put(ability.getName(), ability);
			found = ability;
		}
		return found;
	}

	public String toString() {
		return String
				.format("id:%s, type:%s, name:%s, owner:%s, start:%s, end:%s, duration:%ss, lineStart:%s, lineEnd:%s, lines:%s",
						id, type, name, owner, DateFormat.getTimeInstance().format(start.getTime()), DateFormat
								.getTimeInstance().format(end.getTime()), duration / 1000, lineStart, lineEnd,
						getLineCount());
	}
}
