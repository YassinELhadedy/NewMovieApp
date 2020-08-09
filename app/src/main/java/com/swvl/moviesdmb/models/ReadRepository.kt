package com.swvl.moviesdmb.models


interface ReadRepository<out T> : GetRepository<T>, GetAllRepository<T>