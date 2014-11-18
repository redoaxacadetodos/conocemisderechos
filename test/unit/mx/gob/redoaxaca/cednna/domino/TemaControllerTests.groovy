package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(TemaController)
@Mock(Tema)
class TemaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tema/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.temaInstanceList.size() == 0
        assert model.temaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.temaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.temaInstance != null
        assert view == '/tema/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tema/show/1'
        assert controller.flash.message != null
        assert Tema.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tema/list'

        populateValidParams(params)
        def tema = new Tema(params)

        assert tema.save() != null

        params.id = tema.id

        def model = controller.show()

        assert model.temaInstance == tema
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tema/list'

        populateValidParams(params)
        def tema = new Tema(params)

        assert tema.save() != null

        params.id = tema.id

        def model = controller.edit()

        assert model.temaInstance == tema
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tema/list'

        response.reset()

        populateValidParams(params)
        def tema = new Tema(params)

        assert tema.save() != null

        // test invalid parameters in update
        params.id = tema.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tema/edit"
        assert model.temaInstance != null

        tema.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tema/show/$tema.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tema.clearErrors()

        populateValidParams(params)
        params.id = tema.id
        params.version = -1
        controller.update()

        assert view == "/tema/edit"
        assert model.temaInstance != null
        assert model.temaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tema/list'

        response.reset()

        populateValidParams(params)
        def tema = new Tema(params)

        assert tema.save() != null
        assert Tema.count() == 1

        params.id = tema.id

        controller.delete()

        assert Tema.count() == 0
        assert Tema.get(tema.id) == null
        assert response.redirectedUrl == '/tema/list'
    }
}
