package comprmto.rickyandmorty.domain.repository

import androidx.paging.PagingData
import comprmto.rickyandmorty.data.remote.dto.character.CharacterData
import comprmto.rickyandmorty.data.remote.dto.episode.EpisodeDto
import comprmto.rickyandmorty.data.remote.dto.episode.EpisodeResult
import comprmto.rickyandmorty.data.remote.dto.location.LocationResults
import comprmto.rickyandmorty.domain.model.LocationDomain
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path

interface RickAndMortyRepository {

    suspend fun getAllCharacters(): Flow<PagingData<CharacterData>>

    suspend fun getCharacterDetailById(characterId: Int): CharacterData

    suspend fun getAllLocation(): Flow<PagingData<LocationDomain>>

    suspend fun getLocationDetailById(locationId: Int): LocationResults

    suspend fun getAllEpisode(): EpisodeDto

    suspend fun getEpisodeById(@Path("id") episodeId: Int): EpisodeResult
}