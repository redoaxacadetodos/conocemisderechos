package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(TipoController)
@Mock(Tipo)
class TipoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tipo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tipoInstanceList.size() == 0
        assert model.tipoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tipoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tipoInstance != null
        assert view == '/tipo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tipo/show/1'
        assert controller.flash.message != null
        assert Tipo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tipo/list'

        populateValidParams(params)
        def tipo = new Tipo(params)

        assert tipo.save() != null

        params.id = tipo.id

        def model = controller.show()

        assert model.tipoInstance == tipo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tipo/list'

        populateValidParams(params)
        def tipo = new Tipo(params)

        assert tipo.save() != null

        params.id = tipo.id

        def model = controller.edit()

        assert model.tipoInstance == tipo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tipo/list'

        response.reset()

        populateValidParams(params)
        def tipo = new Tipo(params)

        assert tipo.save() != null

        // test invalid parameters in update
        params.id = tipo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tipo/edit"
        assert model.tipoInstance != null

        tipo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tipo/show/$tipo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tipo.clearErrors()

        populateValidParams(params)
        params.id = tipo.id
        params.version = -1
        controller.update()

        assert view == "/tipo/edit"
        assert model.tipoInstance != null
        assert model.tipoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tipo/list'

        response.reset()

        populateValidParams(params)
        def tipo = new Tipo(params)

        assert tipo.save() != null
        assert Tipo.count() == 1

        params.id = tipo.id

        controller.delete()

        assert Tipo.count() == 0
        assert Tipo.get(tipo.id) == null
        assert response.redirectedUrl == '/tipo/list'
    }
}
