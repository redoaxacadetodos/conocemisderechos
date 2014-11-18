package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(DistritoController)
@Mock(Distrito)
class DistritoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/distrito/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.distritoInstanceList.size() == 0
        assert model.distritoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.distritoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.distritoInstance != null
        assert view == '/distrito/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/distrito/show/1'
        assert controller.flash.message != null
        assert Distrito.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/distrito/list'

        populateValidParams(params)
        def distrito = new Distrito(params)

        assert distrito.save() != null

        params.id = distrito.id

        def model = controller.show()

        assert model.distritoInstance == distrito
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/distrito/list'

        populateValidParams(params)
        def distrito = new Distrito(params)

        assert distrito.save() != null

        params.id = distrito.id

        def model = controller.edit()

        assert model.distritoInstance == distrito
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/distrito/list'

        response.reset()

        populateValidParams(params)
        def distrito = new Distrito(params)

        assert distrito.save() != null

        // test invalid parameters in update
        params.id = distrito.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/distrito/edit"
        assert model.distritoInstance != null

        distrito.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/distrito/show/$distrito.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        distrito.clearErrors()

        populateValidParams(params)
        params.id = distrito.id
        params.version = -1
        controller.update()

        assert view == "/distrito/edit"
        assert model.distritoInstance != null
        assert model.distritoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/distrito/list'

        response.reset()

        populateValidParams(params)
        def distrito = new Distrito(params)

        assert distrito.save() != null
        assert Distrito.count() == 1

        params.id = distrito.id

        controller.delete()

        assert Distrito.count() == 0
        assert Distrito.get(distrito.id) == null
        assert response.redirectedUrl == '/distrito/list'
    }
}
