/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.artur.pokemonapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.artur.pokemonapp.util.TestUtil
import com.artur.pokemonapp.util.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndRead() {
        val pokemon = TestUtil.createPokemon()
        db.pokemonDao().insert(pokemon)
        val loaded = db.pokemonDao().findById(pokemon.id).getOrAwaitValue()
        assertThat(loaded, notNullValue())
        assertThat(loaded.id, `is`(pokemon.id))
        assertThat(loaded.name, `is`(pokemon.name))
        assertThat(loaded.imagePath, `is`(pokemon.imagePath))
    }

    @Test
    fun insertListAndRead() {
        val pokemonList = TestUtil.createPokemonList(2)
        db.pokemonDao().insertAll(pokemonList)
        val loaded = db.pokemonDao().getAll().getOrAwaitValue()
        assertThat(loaded, notNullValue())
        assertThat(loaded.size, `is`(pokemonList.size))

        val first = pokemonList.first()
        val second = pokemonList[1]

        assertThat(loaded.first().id, `is`(first.id))
        assertThat(loaded.first().name, `is`(first.name))
        assertThat(loaded.first().imagePath, `is`(first.imagePath))

        assertThat(loaded[1].id, `is`(second.id))
        assertThat(loaded[1].name, `is`(second.name))
        assertThat(loaded[1].imagePath, `is`(second.imagePath))
    }

}
