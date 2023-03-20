import com.assessment.domain.keyword.SearchKeyword
import com.assessment.domain.keyword.SearchKeywordRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class SearchKeywordJpaRepositoryImpl(
    private val searchKeywordJpaRepository: SearchKeywordJpaRepository
): SearchKeywordRepository {
    override fun save(searchKeyword: SearchKeyword): SearchKeyword {
        return searchKeywordJpaRepository.save(searchKeyword)
    }

    override fun findByKeyword(keyword: String): SearchKeyword? {
        return searchKeywordJpaRepository.findByKeyword(keyword)
    }

    override fun findPopularKeywords(): List<SearchKeyword> {
        TODO("Not yet implemented")
    }
}

interface SearchKeywordJpaRepository: JpaRepository<SearchKeyword, Long> {
    fun findByKeyword(keyword: String): SearchKeyword?
    fun findTop10ByOrderByCountDesc(): List<SearchKeyword>
}