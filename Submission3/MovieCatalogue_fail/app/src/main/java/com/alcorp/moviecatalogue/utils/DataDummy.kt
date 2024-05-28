package com.alcorp.moviecatalogue.utils

import com.alcorp.moviecatalogue.R
import com.alcorp.moviecatalogue.data.source.local.entity.MovieEntity
import com.alcorp.moviecatalogue.data.source.local.entity.TvShowEntity
import java.util.ArrayList

object DataDummy {

    fun generateDummyMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
            "238",
            "The Godfather",
            "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
                false,
            "1972-03-14",
            "https://image.tmdb.org/t/p/original/3bhkrj58Vtu7enYsRolD1fZdja1.jpg"
        )
        )

        movies.add(
            MovieEntity(
                "m2",
                "The Wizard of Oz (1939)",
                "A young kansas girl who dreams of a better life somewhere above the rainbow is caught in the eye of a tornado and lands in the fantasy world of Oz.",
                false,
                "1939",
                R.drawable.movie2.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m3",
                "Citizen Kane",
                "A very wealthy newspaper entrepreneur named Charles Foste Kane died. Kane uttered the mysterious last word, Rosebud. A reporter tried to figure out what his last word meant. He keeps track of the people Kane works and lives with. They then tell what is known about the king of media.",
                false,
                "1941",
                R.drawable.movie3.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m4",
                "The Shawshank Redemption",
                "Two slaves were imprisoned for several years, finding solace & an act of redemption with general decency. They show the importance of Hope which we all need sometimes.",
                false,
                "1994",
                R.drawable.movie4.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m5",
                "The Shawshank Redemption",
                "In the underground criminal world, a series of incidents finally link the lives of two Los Angeles mobsters, the wife of a gangster, a boxer and two petty criminals.",
                false,
                "1994",
                R.drawable.movie5.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m6",
                "Casablanca",
                "A classic film set in Africa during the early days of World War II when an American expat meets her former lover, with unexpected complications.",
                false,
                "1942",
                R.drawable.movie6.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m7",
                "The Godfather: Part II",
                "Michael, son of Vito Corleone, strives to continue to expand his family's 'empire'. Even though he had reached a business deal with Hyman Roth, ganster, he was unaware of the dangers that awaited him.",
                false,
                "1974",
                R.drawable.movie7.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m8",
                "There Will Be Blood",
                "Daniel Plainview, a miner, attempted to master various oil wells across America. One day, he met Paul Sunday who told him the source of the oil in his father's field.",
                false,
                "2007",
                R.drawable.movie8.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m9",
                "2001: A Space Odyssey",
                "2001: A Space Odyssey is a film that runs without a plot, telling big themes from human origins to extraterrestrial life.",
                false,
                "1968",
                R.drawable.movie9.toString()
        )
        )

        movies.add(
            MovieEntity(
                "m10",
                "Star Wars Episode I: The Phantom Menace",
                "Two Jedi Knights are on a mission to find someone who can bring peace to the Force. The search ends when they find a talented boy. However, Sith returned to seize power.",
                false,
                "1999",
                R.drawable.movie10.toString()
        )
        )

        return movies
    }

    fun generateDummyTvShow(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                "tv1",
                "Breaking Bad",
                "Breaking Bad tells the story of a high school chemistry teacher in Albuquerque, New Mexico, named Walter White who is poor and must increase his income by working as a car washer. He is married and has one child. When it was discovered that his wife was pregnant with a second child, White was suddenly diagnosed with lung cancer. Because he was forced, he made money by cooking and selling methamphetamine with one of his former students, Jesse Pinkman.",
                false,
                "2008–2013",
                R.drawable.tv_show1.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv2",
                "Game of Thrones",
                "Game of Thrones tells of the conflict over the throne in a fictional continent called Westeros and Essos.",
                false,
                "2011-2019",
                R.drawable.tv_show2.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv3",
                "The Wire",
                "The Wire is a crime-themed series in the city of Baltimore. This series tells how fiercely the justice enforcers fight criminals who run the illicit business of selling drugs.",
                false,
                "2002–2008",
                R.drawable.tv_show3.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv4",
                "Rick and Morty",
                "The show revolves around the adventures of the members of the Smith household, which consists of parents Jerry and Beth, their children Summer and Morty, and Beth's father, Rick Sanchez, who lives with them as a guest. According to Justin Roiland, the family lives outside of Seattle, Washington. The adventures of Rick and Morty, however, take place across an infinite number of realities, with the characters travelling to other planets and dimensions through portals and Rick's flying car.",
                false,
                "2013–present",
                R.drawable.tv_show4.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv5",
                "The Sopranos",
                "Set in New Jersey and New York City between 1999 and 2007, the series follows Tony Soprano, a New Jersey-based Italian-American mobster, who tries to balance his family life with his role as boss of the Soprano family. Suffering from panic attacks, Tony engages in therapy sessions with psychiatrist Jennifer Melfi off and on throughout the series. Tony eventually finds himself at odds with his uncle Junior, his wife Carmela, other mobsters within the Soprano family, and the New York City-based Lupertazzi family, putting his life at risk.",
                false,
                "1999–2007",
                R.drawable.tv_show5.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv6",
                "Avatar: The Last Airbender",
                "Aang, a 12 year old boy, must put his childhood aside to prevent chaos caused by the Fire Nation. He also struggled to restore harmony between the Water, Earth and Wind Nation.",
                false,
                "2005–2008",
                R.drawable.tv_show6.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv7",
                "Sherlock",
                "Sherlock tells the story between Holmes and Watson. They both are faced with a matter about a mystery in 21st century London that must be solved.",
                false,
                "2010–present",
                R.drawable.tv_show7.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv8",
                "True Detective",
                "True Detective tells the story of two homicide detectives in the Criminal Investigation division of the Louisiana State Police. These two detectives were chasing a serial killer in Louisiana for 17 years.",
                false,
                "2014–present",
                R.drawable.tv_show8.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "tv9",
                "Firefly",
                "The show explores the lives of a group of people, some of whom fought on the losing side of a civil war, who make a living on the fringes of society as part of the pioneer culture of their star system. In this future, the only two surviving superpowers, the United States and China, fused to form the central federal government, called the Alliance, resulting in the fusion of the two cultures. According to Whedon's vision, nothing will change in the future: technology will advance, but we will still have the same political, moral, and ethical problems as today.",
                false,
                "2002–2003",
                R.drawable.tv_show9.toString()
        )
        )

        tvShow.add(
            TvShowEntity(
                "456",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                false,
                "1989-12-16",
                "https://image.tmdb.org/t/p/original/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg"
        )
        )

        return tvShow
    }
}