package com.klezovich.springmvccoaching.optional;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//Based on https://www.baeldung.com/java-optional
class OptionalDemoTest {

    @Test
    void testCanCreateEmptyOptional() {
        var opt = Optional.empty();
        assertTrue(opt.isEmpty());
        assertFalse(opt.isPresent());
    }

    @Test
    void testCanCreateOptionalWithValue() {
        var opt = Optional.of("abc");
        assertFalse(opt.isEmpty());
        assertTrue(opt.isPresent());
        assertEquals("abc", opt.get());
    }

    @Test
    void testAttemptToCreateOptionalWithNullThrowsException() {
        assertThrows(NullPointerException.class, () -> Optional.of(null));
    }

    @Test
    void testCanCreateOptionalWithNullIfUseSpecialMethod() {
        var opt = Optional.ofNullable(null);
        assertTrue(opt.isEmpty());
        assertFalse(opt.isPresent());
    }

    @Test
    void testCanRunCodeOnlyIfOptionalPresent() {
        var opt = Optional.of("abc");
        opt.ifPresent( name -> System.out.println(name.length()));

        opt = Optional.empty();
        opt.ifPresent( name -> System.out.println("Did code run for an empty optional?"));
    }

    @Test
    void testCanSetDefaultValue() {
        var opt = Optional.empty();
        var name = opt.orElse("victor");
        assertEquals("victor", name);

        opt = Optional.of("robert");
        name = opt.orElse("victor");
        assertEquals("robert",name);
    }

    @Test
    void testCanUseSupplierForADefaultValue() {
        var opt = Optional.empty();
        var name = opt.orElseGet( () -> "a" + "b" + "c");
        assertEquals("abc", name);
    }

    @Test
    void testCanThrowExceptionIfValueAbsent() {
        var opt = Optional.empty();
        assertThrows(NoSuchElementException.class, () -> opt.orElseThrow());

        var opt1 = Optional.of("abc");
        assertEquals("abc", opt1.orElseThrow());
    }
}