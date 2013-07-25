package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(EstadoController)
@Mock(Estado)
class EstadoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/estado/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.estadoInstanceList.size() == 0
        assert model.estadoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.estadoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.estadoInstance != null
        assert view == '/estado/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/estado/show/1'
        assert controller.flash.message != null
        assert Estado.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/estado/list'

        populateValidParams(params)
        def estado = new Estado(params)

        assert estado.save() != null

        params.id = estado.id

        def model = controller.show()

        assert model.estadoInstance == estado
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/estado/list'

        populateValidParams(params)
        def estado = new Estado(params)

        assert estado.save() != null

        params.id = estado.id

        def model = controller.edit()

        assert model.estadoInstance == estado
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/estado/list'

        response.reset()

        populateValidParams(params)
        def estado = new Estado(params)

        assert estado.save() != null

        // test invalid parameters in update
        params.id = estado.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/estado/edit"
        assert model.estadoInstance != null

        estado.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/estado/show/$estado.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        estado.clearErrors()

        populateValidParams(params)
        params.id = estado.id
        params.version = -1
        controller.update()

        assert view == "/estado/edit"
        assert model.estadoInstance != null
        assert model.estadoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/estado/list'

        response.reset()

        populateValidParams(params)
        def estado = new Estado(params)

        assert estado.save() != null
        assert Estado.count() == 1

        params.id = estado.id

        controller.delete()

        assert Estado.count() == 0
        assert Estado.get(estado.id) == null
        assert response.redirectedUrl == '/estado/list'
    }
}
