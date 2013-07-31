package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(UnidadEjecutoraController)
@Mock(UnidadEjecutora)
class UnidadEjecutoraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/unidadEjecutora/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.unidadEjecutoraInstanceList.size() == 0
        assert model.unidadEjecutoraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.unidadEjecutoraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.unidadEjecutoraInstance != null
        assert view == '/unidadEjecutora/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/unidadEjecutora/show/1'
        assert controller.flash.message != null
        assert UnidadEjecutora.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadEjecutora/list'

        populateValidParams(params)
        def unidadEjecutora = new UnidadEjecutora(params)

        assert unidadEjecutora.save() != null

        params.id = unidadEjecutora.id

        def model = controller.show()

        assert model.unidadEjecutoraInstance == unidadEjecutora
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadEjecutora/list'

        populateValidParams(params)
        def unidadEjecutora = new UnidadEjecutora(params)

        assert unidadEjecutora.save() != null

        params.id = unidadEjecutora.id

        def model = controller.edit()

        assert model.unidadEjecutoraInstance == unidadEjecutora
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadEjecutora/list'

        response.reset()

        populateValidParams(params)
        def unidadEjecutora = new UnidadEjecutora(params)

        assert unidadEjecutora.save() != null

        // test invalid parameters in update
        params.id = unidadEjecutora.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/unidadEjecutora/edit"
        assert model.unidadEjecutoraInstance != null

        unidadEjecutora.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/unidadEjecutora/show/$unidadEjecutora.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        unidadEjecutora.clearErrors()

        populateValidParams(params)
        params.id = unidadEjecutora.id
        params.version = -1
        controller.update()

        assert view == "/unidadEjecutora/edit"
        assert model.unidadEjecutoraInstance != null
        assert model.unidadEjecutoraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/unidadEjecutora/list'

        response.reset()

        populateValidParams(params)
        def unidadEjecutora = new UnidadEjecutora(params)

        assert unidadEjecutora.save() != null
        assert UnidadEjecutora.count() == 1

        params.id = unidadEjecutora.id

        controller.delete()

        assert UnidadEjecutora.count() == 0
        assert UnidadEjecutora.get(unidadEjecutora.id) == null
        assert response.redirectedUrl == '/unidadEjecutora/list'
    }
}
