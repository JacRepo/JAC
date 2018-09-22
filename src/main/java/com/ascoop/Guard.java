package com.ascoop;

import java.util.function.Supplier;

public abstract class Guard {
	abstract boolean evaluate();

	static public Guard convert(Supplier<Boolean> s) {
		return new PureExpressionGuard(s);
	}

	static public <V> Guard convert(Future<V> f, Actor awaitingActors) {
		return new FutureGuard<>(f, awaitingActors);
	}

// probably we should define and(Guard...) and or(Guard...) instead of objects
//	static private Guard convert(Object o) {
//		if (o instanceof Supplier) {
//			//noinspection unchecked
//			return convert((Supplier<Boolean>) o);
//		} else if (o instanceof Future) {
//			return convert((Future) o); // must pass an actor ref!
//		} else if (o instanceof Guard) {
//			return (Guard) o;
//		} else {
//			System.out.println("Cannot make guard");
//			throw new IllegalArgumentException("Cannot make a guard.");
//		}
//	}
//
//	public static Guard and(Object... guards) {
//		if (guards.length == 0)
//			return null;
//		Guard g = Guard.convert(guards[0]);
//		for (int i = 1; i < guards.length; i++) {
//			g = new ConjunctionGuard(g, Guard.convert(guards[i]));
//		}
//		return g;
//	}
//
//	public static Guard or(Object... guards) {
//		if (guards.length == 0)
//			return null;
//		Guard g = Guard.convert(guards[0]);
//		for (int i = 1; i < guards.length; i++) {
//			g = new DisjunctionGuard(g, Guard.convert(guards[i]));
//		}
//		return g;
//	}


}
