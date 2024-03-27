package com.wisemen.chargehub

import StringResources
import dev.icerock.moko.resources.ResourceContainer
import dev.icerock.moko.resources.StringResource
import org.koin.java.KoinJavaComponent.inject

fun StringResource.toStringResource(args: List<Any> = listOf()) : String {
    val stringResources: StringResources by inject(StringResources::class.java)

    return stringResources.get(this, args)
}