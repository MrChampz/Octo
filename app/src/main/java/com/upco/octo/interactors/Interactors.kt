package com.upco.octo.interactors

import com.upco.interactors.*

class Interactors(
    val getRepos: GetRepos,
    val getOpenRepo: GetOpenRepo,
    val setOpenRepo: SetOpenRepo,
    val getSearchUsername: GetSearchUsername,
    val setSearchUsername: SetSearchUsername
)