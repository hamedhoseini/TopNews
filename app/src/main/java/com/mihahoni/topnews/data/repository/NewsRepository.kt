package com.mihahoni.topnews.data.repository

interface NewsRepository {
   suspend fun getServiceFromRemote()
}