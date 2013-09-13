package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(UnidadMedidaController)
@Mock(UnidadMedida)
class UnidadMedidaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/unidadMedida/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.unidadMedidaInstanceList.size() == 0
        assert model.unidadMedidaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.unidadMedidaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.unidadMedidaInstance != null
        assert view == '/unidadMedida/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/unidadMedida/show/1'
        assert controller.flash.message != null
        assert UnidadMedida.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadMedida/list'

        populateValidParams(params)
        def unidadMedida = new UnidadMedida(params)

        assert unidadMedida.save() != null

        params.id = unidadMedida.id

        def model = controller.show()

        assert model.unidadMedidaInstance == unidadMedida
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadMedida/list'

        populateValidParams(params)
        def unidadMedida = new UnidadMedida(params)

        assert unidadMedida.save() != null

        params.id = unidadMedida.id

        def model = controller.edit()

        assert model.unidadMedidaInstance == unidadMedida
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/unidadMedida/list'

        response.reset()

        populateValidParams(params)
        def unidadMedida = new UnidadMedida(params)

        assert unidadMedida.save() != null

        // test invalid parameters in update
        params.id = unidadMedida.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/unidadMedida/edit"
        assert model.unidadMedidaInstance != null

        unidadMedida.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/unidadMedida/show/$unidadMedida.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        unidadMedida.clearErrors()

        populateValidParams(params)
        params.id = unidadMedida.id
        params.version = -1
        controller.update()

        assert view == "/unidadMedida/edit"
        assert model.unidadMedidaInstance != null
        assert model.unidadMedidaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/unidadMedida/list'

        response.reset()

        populateValidParams(params)
        def unidadMedida = new UnidadMedida(params)

        assert unidadMedida.save() != null
        assert UnidadMedida.count() == 1

        params.id = unidadMedida.id

        controller.delete()

        assert UnidadMedida.count() == 0
        assert UnidadMedida.get(unidadMedida.id) == null
        assert response.redirectedUrl == '/unidadMedida/list'
    }
}
