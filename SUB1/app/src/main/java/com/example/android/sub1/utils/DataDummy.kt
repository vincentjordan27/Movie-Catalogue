package com.example.android.sub1.utils

import com.example.android.sub1.data.source.local.entity.MovieModel
import com.example.android.sub1.data.source.local.entity.TvShowModel

object DataDummy {
    fun generateMovieDummy() : List<MovieModel>{
        val movies = ArrayList<MovieModel>()

        movies.add(
            MovieModel(
                1,
                "Underwater",
                "English",
                6.4,
                "After an earthquake destroys their underwater station, six researchers must navigate two miles along the dangerous, unknown depths of the ocean floor to make it to safety in a race against time",
                "2020-01-08",
                "https://image.tmdb.org/t/p/w500/gzlbb3yeVISpQ3REd3Ga1scWGTU.jpg"
            )
        )

        movies.add(
            MovieModel(
                2,
                "Sonic the Hedgehog",
                "English",
                7.6,
                "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
                "2020-02-12",
                "https://image.tmdb.org/t/p/w500/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg"
            )
        )

        movies.add(
            MovieModel(
                3,
                "Bloodshot",
                "English",
                7.2,
                "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. As Ray first trains with fellow super-soldiers, he cannot recall anything from his former life. But when his memories flood back and he remembers the man that killed both him and his wife, he breaks out of the facility to get revenge, only to discover that there's more to the conspiracy than he thought.",
                "2020-03-05",
                "https://image.tmdb.org/t/p/w500//8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg"
            )
        )

        movies.add(
            MovieModel(
                4,
                "Onward",
                "English",
                8.0,
                "In a suburban fantasy world, two teenage elf brothers embark on an extraordinary quest to discover if there is still a little magic left out there.",
                "2020-02-29",
                "https://image.tmdb.org/t/p/w500/7W0G3YECgDAfnuiHG91r8WqgIOe.jpg"
            )
        )

        movies.add(
            MovieModel(
                5,
                "Trolls World Tour",
                "English",
                7.7,
                "Queen Poppy and Branch make a surprising discovery — there are other Troll worlds beyond their own, and their distinct differences create big clashes between these various tribes. When a mysterious threat puts all of the Trolls across the land in danger, Poppy, Branch, and their band of friends must embark on an epic quest to create harmony among the feuding Trolls to unite them against certain doom.",
                "2020-03-12",
                "https://image.tmdb.org/t/p/w500/7W0G3YECgDAfnuiHG91r8WqgIOe.jpg"
            )
        )

        return movies
    }

    fun generateTvShowDummy() : List<TvShowModel>{
        val tvShow = ArrayList<TvShowModel>()

        tvShow.add(
            TvShowModel(
                1,
                "Siren",
                "English",
                7.7,
                "The coastal town of Bristol Cove is known for its legend of once being home to mermaids. When the arrival of a mysterious girl proves this folklore all too true, the battle between man and sea takes a very vicious turn as these predatory beings return to reclaim their right to the ocean.",
                "2018-03-29",
                "https://image.tmdb.org/t/p/w500/k906XXqqFMT93v2WMkIOtUcEAlV.jpg"

            )
        )

        tvShow.add(
            TvShowModel(
                2,
                "Brooklyn Nine-Nine",
                "English",
                7.8,
                "A single-camera ensemble comedy following the lives of an eclectic group of detectives in a New York precinct, including one slacker who is forced to shape up when he gets a new boss.",
                "2013-09-17",
                "https://image.tmdb.org/t/p/w500/dzj0oLZWe3qMgK4jlgdKWPVxxIx.jpg"
            )
        )

        tvShow.add(
            TvShowModel(
                3,
                "Keeping Up with the Kardashians",
                "English",
                4.7,
                "A peek inside the exploits and privileged private lives of the blended Kardashian-Jenner family, including sisters Kim, Kourtney and Khloé.",
                "2007-10-14",
                "https://image.tmdb.org/t/p/w500/l7QHRrX1EYgQAzNJdCdoEQHoHJ.jpg"
            )
        )


        tvShow.add(
            TvShowModel(
                4,
                "SEAL Team",
                "English",
                7.4,
                "The lives of the elite Navy Seals as they train, plan and execute the most dangerous, high-stakes missions our country can ask.",
                "2017-09-27",
                "https://image.tmdb.org/t/p/w500//v0G8cbPtGoiTBFb4HW1hAJJFMol.jpg"
            )
        )

        tvShow.add(
            TvShowModel(
                5,
                "What We Do in the Shadows",
                "English",
                7.1,
                "A documentary-style look into the daily (or rather, nightly) lives of three vampires in Staten Island who have “lived” together for hundreds and hundreds of years.",
                "2019-03-27",
                "https://image.tmdb.org/t/p/w500/aEwa2kqu5XORbmMwdhEHufODcIr.jpg"
            )
        )


        return tvShow
    }

    fun generateFavMovie() : List<MovieModel>{
        return generateMovieDummy()
    }

    fun generateFavTvShow() : List<TvShowModel>{
        return generateTvShowDummy()
    }


}