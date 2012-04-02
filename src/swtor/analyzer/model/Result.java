package swtor.analyzer.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Result extends GenericMetricEntity {

	public enum ResultType {
		IN_COMBAT, OUT_OF_COMBAT;
	}

	private String id;
	private ResultType type = ResultType.OUT_OF_COMBAT;
	private String name = "Result: ";
	private String owner;
	private Calendar start;
	private Calendar end;
	private long duration;

	private long logEntryCount;
	private long lineStart;
	private long lineEnd;

	private final Map<String, Actor> actors = new HashMap<>();
	private final Map<String, Ability> abilities = new HashMap<>();

	public Result(long lineStart) {
		this.lineStart = lineStart;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public long getLogEntryCount() {
		return logEntryCount;
	}

	public void setLogEntryCount(long logEntryCount) {
		this.logEntryCount = logEntryCount;
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

	public void increaseLogEntryCount() {
		++logEntryCount;
	}

	public Actor getActor(Actor actor) {
		Actor found = actors.get(actor.getName());
		if (found == null) {
			actors.put(actor.getName(), actor);
			found = actor;
		}
		return found;
	}

	public Ability getAbility(Ability ability) {
		Ability found = abilities.get(ability.getName());
		if (found == null) {
			abilities.put(ability.getName(), ability);
			found = ability;
		}
		return found;
	}

	public String toString() {
		return String
				.format("id:%s, type:%s, name:%s, owner:%s, start:%s, end:%s, duration:%s, lineStart:%s, lineEnd:%s, entry#:%s",
						id, type, name, owner, DateFormat.getTimeInstance().format(start.getTime()), DateFormat
								.getTimeInstance().format(end.getTime()), duration / 1000, lineStart, lineEnd,
						logEntryCount);
	}
}
