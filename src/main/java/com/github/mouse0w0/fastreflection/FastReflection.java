package com.github.mouse0w0.fastreflection;

import com.github.mouse0w0.fastreflection.accassor.*;
import com.github.mouse0w0.fastreflection.util.UnsafeReflections;
import com.github.mouse0w0.fastreflection.util.UnsafeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

public interface FastReflection {

    /**
     * Creating an {@code FieldAccessor} based on asm
     * using {@link AsmFieldAccessor} if your method is accessible and non-final.
     * For inaccessible or final field, will return a {@link ReflectFieldAccessor}.
     *
     * @param field the field accessing to
     * @return a {@code FieldAccessor} object based on reflection or asm
     * @throws Exception may throw any exception while creating AsmFieldAccessor
     * or a null object was given
     */
    static FieldAccessor create(Field field) throws Exception {
        return create(field, false);
    }

    /**
     * Creating an {@code FieldAccessor} based on asm
     * using {@link AsmFieldAccessor} if your method is accessible and non-final.
     * For inaccessible or non-final field, will create a accessor
     * use {@link ReflectFieldAccessor}. For final and non-volatile field,
     * will create a accessor use {@link UnsafeFieldAccessor},
     * {@link UnsafeStaticFieldAccessor}. For final and volatile
     * field, will create a accessor use {@link UnsafeVolatileFieldAccessor},
     * {@link UnsafeStaticVolatileFieldAccessor}.
     *
     * @param field the field accessing to
     * @param unsafe whether {@code unfinal} field using {@link UnsafeReflections} or else
     * @return a {@code FieldAccessor} object based on reflection or asm
     * @throws Exception may throw any exception while creating AsmFieldAccessor
     * or a null object was given
     */
    static FieldAccessor create(Field field, boolean unsafe) throws Exception {
        int modifiers = field.getModifiers();

        // For public and non-final field
        if (Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers))
            return AsmFieldAccessor.create(field);

        // Using unsafe accessor
        if (unsafe && UnsafeUtils.isUnsafeSupported()) {
            if (Modifier.isVolatile(modifiers))
                // for volatile field
                return Modifier.isStatic(modifiers) ? new UnsafeStaticVolatileFieldAccessor(field)
                        : new UnsafeVolatileFieldAccessor(field);
            else
                return Modifier.isStatic(modifiers) ? new UnsafeStaticFieldAccessor(field)
                        : new UnsafeFieldAccessor(field);
        }

        // if unsafe unsupported
        if (unsafe) UnsafeReflections.unfinal(field);

        return new ReflectFieldAccessor(field);
    }

    /**
     * Creating an {@code MethodAccessor} based on asm
     * using {@link AsmMethodAccessor} if your method is accessible.
     * For inaccessible method, will return a {@link ReflectMethodAccessor}.
     *
     * @param method the method accessing to
     * @return a {@code MethodAccessor} object based on reflection or asm
     * @throws Exception may throw any exception while creating AsmMethodAccessor
     * or a null object was given
     */
    static MethodAccessor create(Method method) throws Exception {
        return Modifier.isPublic(method.getModifiers()) ? AsmMethodAccessor.create(method) : new ReflectMethodAccessor(method);
    }

    /**
     * Creating an {@code ConstructorAccessor} based on asm
     * using {@link AsmConstructorAccessor} if your method is accessible.
     * For inaccessible constructor, will return a {@link ReflectConstructorAccessor}.
     *
     * @param constructor the constructor accessing to
     * @return a {@code ConstructorAccessor} object based on reflection or asm
     * @throws Exception may throw any exception while creating AsmConstructorAccessor
     * or a null object was given
     */
    static <T> ConstructorAccessor<T> create(Constructor<T> constructor) throws Exception {
        return Modifier.isPublic(constructor.getModifiers()) ? AsmConstructorAccessor.create(constructor) : new ReflectConstructorAccessor<>(constructor);
    }
}
