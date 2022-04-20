import com.writers.noteapp.domain.Note
import com.writers.noteapp.dto.NoteDto
import com.writers.noteapp.repository.NoteRepository
import com.writers.noteapp.service.NoteService
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification

class NoteServiceSpec extends Specification {

    NoteRepository noteRepository = Mock(NoteRepository)
    ConversionService conversionService = Mock(ConversionService)
    NoteService noteService = new NoteService(noteRepository, conversionService)

    def "findAll"() {
        given: "a request for a page"
            Pageable pageable = PageRequest.of(0, 10, Sort.by("DESC", "id"))
        when: "the method is called"
            noteService.findAll(pageable)
        then: "the repository method is called once"
            1 * noteRepository.findAll(pageable) >> new PageImpl<>([new Note(), new Note()], pageable, 2)
    }

    def "save"() {
        given: "a request to save a note"
            NoteDto noteDto = new NoteDto()
        when: "the method is called"
            noteService.save(noteDto)
        then: "the repository method is called once"
            1 * noteRepository.save(_)
    }

    def "delete"() {
        when: "the method is called"
            noteService.deleteById(1L)
        then: "the repository method is called once with the given id"
            1 * noteRepository.deleteById(1L)
    }
}