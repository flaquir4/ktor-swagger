package de.nielsfalk.ktor.swagger

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Create a parameterized type instance.
 * @param raw the raw class to create a parameterized type instance for
 * @param typeArguments the mapping used for parameterization
 * @return [ParameterizedType]
 */
internal fun parameterize(raw: Class<*>, vararg typeArguments: Type): ParameterizedType {

    @Suppress("UNCHECKED_CAST")
    return ParameterizedTypeImpl(raw, null, typeArguments as Array<Type>)
}

/**
 * Implementation of [ParameterizedType].
 * @see parameterize
 */
internal data class ParameterizedTypeImpl
internal constructor(
    private val rawType: Type,
    private val ownerType: Type?,
    private val actualTypeArguments: Array<Type>
) : ParameterizedType {

    override fun getRawType(): Type = rawType

    override fun getOwnerType(): Type? = ownerType

    override fun getActualTypeArguments(): Array<Type> =
        actualTypeArguments.clone()

    override fun equals(other: Any?): Boolean =
        if (other is ParameterizedType) {
            rawType == other.rawType &&
                ownerType == other.ownerType &&
                actualTypeArguments.contentEquals(other.actualTypeArguments)
        } else {
            false
        }
}
